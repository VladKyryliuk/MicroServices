package com.example.microservices.controller;

import com.example.microservices.model.Smartphone;
import com.example.microservices.service.SmartphoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/smartphones/")
@Tag(name = "Controller Smartphones", description = "Full CRUD operations")
public class SmartphoneRestController {
    @Autowired
    SmartphoneService service;
    @GetMapping()
    @Operation(summary = "Get all smartphones", description = "Returns all object")
    List<Smartphone> showAll(){
        return service.getAll();
    }
    @GetMapping("{id}")
    @Operation(summary = "Get smartphone by id", description = "Returns object")
    Smartphone showOne(@Parameter(description = "Unique identifier for object")
                       @PathVariable String id){
        return service.get(id);
    }
    @DeleteMapping("{id}")
    @Operation(summary = "Delete Smartphone by id", description = "Delete object")
    void deleteOne(@Parameter(description = "Unique identifier for object")
                   @PathVariable String id){
        service.del(id);
    }
    @PostMapping()
    @Operation(summary = "Create new Smartphone", description = "Create new object")
    Smartphone create(@RequestBody Smartphone smartphone){
       return service.add(smartphone);
    }
    @PutMapping()
    @Operation(summary = "Update smartphone",description = "Update object")
    Smartphone update(@RequestBody Smartphone smartphone){
        return service.upd(smartphone);
    }
}
