package com.example.microservices.repository;

import com.example.microservices.model.Smartphone;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SmartphoneRepository extends MongoRepository<Smartphone,String> {
    void deleteById(String id);
}
