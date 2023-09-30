package com.example.microservices.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "Smartphone", description = "Smartphone model and brand")
public class Smartphone {
    @Id
    @Schema(name = "id",
    description = "mongoDB identifier",
    example = "650823d7fa2c4130d7210cb1",
    accessMode = Schema.AccessMode.READ_ONLY)
    public String id;
    @Schema(name = "name",
    description = "Name smartphone",
    example = "Galaxy S22 Ultra",
    accessMode = Schema.AccessMode.READ_WRITE)
    public String name;
    @Schema(name = "description",
    description = "Company name",
    example = "Sumsung",
    accessMode = Schema.AccessMode.READ_WRITE)
    public String description;
    @Schema(name = "CeratedAt",
    description = "Information about when the object was created",
    example = "2023-09-23T23:34:54.572",
    accessMode = Schema.AccessMode.READ_WRITE)
    public LocalDateTime createdAt;
    @Schema(name = "updateAt",
    description = "information about when the object was update",
    example = "2023-09-25T14:29:39.772",
    accessMode = Schema.AccessMode.READ_WRITE)
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
