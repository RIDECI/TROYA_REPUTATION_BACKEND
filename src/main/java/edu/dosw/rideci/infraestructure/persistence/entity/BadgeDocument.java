package edu.dosw.rideci.infraestructure.persistence.entity;


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
    private String pathImageBlackAndWhite;
    private String pathImageColor;
    private String description;
    private boolean isActive;

}