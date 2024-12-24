package com.example.sopgrpcbackend;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.UUID;


public class PartRepository {

    private final EntityManagerFactory entityManagerFactory;

    public PartRepository() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("default");
    }

    public Part findById(UUID id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Part part = entityManager.find(Part.class, id);
        entityManager.close();
        return part;
    }

}