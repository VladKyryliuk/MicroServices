package com.example.microservices.service;

import com.example.microservices.model.Smartphone;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SmartphoneService {
    private List<Smartphone> smartphones = new ArrayList<>();

    @PostConstruct
    void init(){
        smartphones.add(new Smartphone("1","Iphone X","Apple"));
        smartphones.add(new Smartphone("2","Galaxy S20","Samsung"));
        smartphones.add(new Smartphone("3","Pixel 6","Google"));
    }

    public List<Smartphone> getAll(){
        return smartphones;
    }

    //Read
    public Smartphone get(String id){
        return smartphones.stream().filter(e -> e.getId()
                .equals(id))
                .findFirst()
                .orElse(null);
    }
    //Delete
    public List<Smartphone> del(String id){
         smartphones = smartphones.stream().filter(e ->!e.getId().equals(id))
                .toList();
        return smartphones;
    }
    //Create
    public void add(Smartphone smartphone){
        smartphones.add(smartphone);
    }
    //Update
    public List<Smartphone> upd(Smartphone smartphone){
         smartphones = smartphones.stream()
                 .map(el->el.getId().equals(smartphone.getId()) ? smartphone:el)
                 .collect(Collectors.toList());
         return smartphones;
    }
}
