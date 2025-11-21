package edu.dosw.rideci.infraestructure.persistence.entity;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BadgeDocument{
    private String name;    
    private String pathImage;
    private String description;
    private boolean isActive;

}