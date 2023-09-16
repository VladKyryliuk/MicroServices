package com.example.microservices.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Smartphone {
    @Id
    public String id;
    public String name;
    public String description;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    public Smartphone(String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Smartphone(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Smartphone(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Smartphone smartphone = (Smartphone) o;
        return Objects.equals(getId(),smartphone.getId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
