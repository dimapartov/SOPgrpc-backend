package com.example.sopgrpcbackend;

import io.grpc.Server;
import io.grpc.ServerBuilder;


public class GrpcServer {

    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder
                .forPort(8082)
                .addService(new OrderPartValidationService())
                .build();

        System.out.println("gRPC сервер запущен на порту 8082");
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Остановка сервера...");
            server.shutdown();
        }));

        server.awaitTermination();
    }

}