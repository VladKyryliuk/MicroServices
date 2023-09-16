package com.example.microservices.controller;

import com.example.microservices.model.Smartphone;
import com.example.microservices.service.SmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/smartphones/")
public class SmartphoneRestController {
    @Autowired
    SmartphoneService service;

    @RequestMapping()
    List<Smartphone> showAll(){
        return service.getAll();
    }

    @RequestMapping("{id}")
    Smartphone showOne(@PathVariable String id){
        return service.get(id);
    }
    @DeleteMapping("{id}")
    void deleteOne(@PathVariable String id){
        service.del(id);
    }
    @PostMapping()
    Smartphone create(@RequestBody Smartphone smartphone){
       return service.add(smartphone);
    }
    @PutMapping()
    Smartphone update(@RequestBody Smartphone smartphone){
        return service.upd(smartphone);
    }
}
