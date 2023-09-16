package com.example.microservices.repository;

import com.example.microservices.model.Smartphone;
import com.mongodb.lang.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SmartphoneRepository extends MongoRepository<Smartphone,String> {
    @NonNull
    void deleteById(String id);
}
