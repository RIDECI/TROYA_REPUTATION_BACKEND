package edu.dosw.rideci.infraestructure.controller.dto.response;



import edu.dosw.rideci.domain.model.Reputation;
import edu.dosw.rideci.domain.model.Vehicle;
import edu.dosw.rideci.domain.model.enums.ProfileType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BadgeResponse {
    private String name;    
    private String pathImage;
    private String description;
    private boolean isActive;
    
}
