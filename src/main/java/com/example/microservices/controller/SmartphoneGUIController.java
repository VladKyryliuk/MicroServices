package com.example.microservices.controller;

import com.example.microservices.model.Smartphone;
import com.example.microservices.service.SmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/ui/v1/smartphones/")
public class SmartphoneGUIController {
    @Autowired
    SmartphoneService service;

    @RequestMapping()
        String showSmartphone(Model model){
        List<Smartphone> el = service.getAll();
        model.addAttribute("el",el);

        return "Smartphone";
    }

    @RequestMapping("del/{id}")
    String deleteElem(@PathVariable("id") String id){
        service.del(id);
        return "redirect:/ui/v1/smartphones/";
    }

    @RequestMapping("edit/{id}")
    String editElem(@PathVariable("id") String id, @RequestParam String name, @RequestParam String description){
        Smartphone smartphone = service.get(id);
        smartphone.setName(name);
        smartphone.setDescription(description);
        smartphone.setUpdatedAt(LocalDateTime.now());
        service.upd(smartphone);
        return "redirect:/ui/v1/smartphones/";
    }

    @RequestMapping("create/")
    String addElem(@RequestParam String name, @RequestParam String description){
        Smartphone smartphone = new Smartphone();
        smartphone.setName(name);
        smartphone.setDescription(description);
        smartphone.setCreatedAt(LocalDateTime.now());
        service.add(smartphone);
        return "redirect:/ui/v1/smartphones/";
    }
}
