package com.example.microservices.repository;

import com.example.microservices.model.Smartphone;
import com.mongodb.lang.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmartphoneRepository extends MongoRepository<Smartphone,String> {
    void deleteById(@NonNull String id);
}
