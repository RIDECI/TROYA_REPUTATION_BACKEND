package edu.dosw.rideci.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Badge {
    
    private String name;    
    private String pathImage;
    private String description;
    private boolean isActive;
    
   
}