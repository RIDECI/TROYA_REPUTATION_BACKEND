package edu.dosw.rideci.infraestructure.controller.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BadgeRequestDTO {
    private String name;    
    private String pathImageBlackAndWhite;
    private String pathImageColor;
    private String description;
    private boolean isActive;
    
}
