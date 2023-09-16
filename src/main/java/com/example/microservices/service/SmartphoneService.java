package com.example.microservices.service;

import com.example.microservices.model.Smartphone;
import com.example.microservices.repository.SmartphoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
//import java.util.ArrayList;
import java.util.List;


@Service
public class SmartphoneService {
//    private List<Smartphone> smartphones = new ArrayList<>();

    @Autowired
    SmartphoneRepository repository;

//    @PostConstruct
//    void init(){
//        smartphones.add(new Smartphone("1","Iphone X","Apple"));
//        smartphones.add(new Smartphone("2","Galaxy S20","Samsung"));
//        smartphones.add(new Smartphone("3","Pixel 6","Google"));
//        repository.saveAll(smartphones);
//    }

    public List<Smartphone> getAll(){
        return repository.findAll();
    }

    //Read
    public Smartphone get(String id){
        return  repository.findById(id).orElse(null);
    }
    //Delete
    public void del(String id){
         repository.deleteById(id);
    }
    //Create
    public Smartphone add(Smartphone smartphone){
        smartphone.setCreatedAt(LocalDateTime.now());
        return repository.save(smartphone);
    }
    //Update
    public Smartphone upd(Smartphone smartphone){
        smartphone.setUpdatedAt(LocalDateTime.now());
        return repository.save(smartphone);
    }
}
