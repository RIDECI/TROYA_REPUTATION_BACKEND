package edu.dosw.rideci.infraestructure.controller.dto.response;

import java.util.List;

import edu.dosw.rideci.domain.model.Calification;
import edu.dosw.rideci.domain.model.Vehicle;
import edu.dosw.rideci.domain.model.enums.ProfileType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileResponseDTO {
    private Long id; //Viene del microservicio de user?
    private String name; //Viene del microservicio de user?
    private List<Vehicle> vehicles;
    private Calification calification;
    private ProfileType profileType;
    
}
