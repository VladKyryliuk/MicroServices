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

    @RequestMapping("hello")
    String sayHello(){
        return "<h1>Hello<h1>";
    }

    @RequestMapping("")
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
    @PostMapping("")
    Smartphone create(@RequestBody Smartphone smartphone){
        service.add(smartphone);
        return smartphone ;
    }
    @PutMapping("")
    Smartphone update(@RequestBody Smartphone smartphone){
        service.upd(smartphone);
        return smartphone;
    }
}
