package com.example.sopgrpcbackend;

import com.example.sop.grpc.generated.OrderPartValidationProto;
import com.example.sop.grpc.generated.OrderPartValidationServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.UUID;

public class OrderPartValidationService extends OrderPartValidationServiceGrpc.OrderPartValidationServiceImplBase {

    private final PartRepository partRepository;

    public OrderPartValidationService() {
        this.partRepository = new PartRepository();
    }

    @Override
    public void validatePart(OrderPartValidationProto.PartValidationRequest request,
                             StreamObserver<OrderPartValidationProto.PartValidationResponse> responseObserver) {
        try {
            UUID partId = UUID.fromString(request.getPartId());

            Part part = partRepository.findById(partId);

            if (part == null) {
                throw new RuntimeException("Part not found");
            }

            if (part.getQuantityOnStorage() < request.getQuantity()) {
                responseObserver.onNext(OrderPartValidationProto.PartValidationResponse.newBuilder()
                        .setIsValid(false)
                        .setErrorMessage("Insufficient stock. Requested: " + request.getQuantity() +
                                ", quantity on storage: " + part.getQuantityOnStorage())
                        .build());
                responseObserver.onCompleted();
                return;
            }


            responseObserver.onNext(OrderPartValidationProto.PartValidationResponse.newBuilder()
                    .setIsValid(true)
                    .build());
            responseObserver.onCompleted();
        } catch (IllegalArgumentException e) {
            responseObserver.onError(new RuntimeException("Invalid UUID format: " + e.getMessage()));
        } catch (Exception e) {
            responseObserver.onError(new RuntimeException("Unexpected error: " + e.getMessage()));
        }
    }
}