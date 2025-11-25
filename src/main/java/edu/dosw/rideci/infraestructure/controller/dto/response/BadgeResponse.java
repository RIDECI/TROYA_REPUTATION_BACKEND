package edu.dosw.rideci.infraestructure.controller.dto.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BadgeResponse {
    private String name;    
    private String pathImageBlackAndWhite;
    private String pathImageColor;
    private String description;
    private boolean isActive;
    
}
