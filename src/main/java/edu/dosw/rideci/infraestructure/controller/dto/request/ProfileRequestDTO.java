package edu.dosw.rideci.infraestructure.controller.dto.request;

import java.util.List;

import edu.dosw.rideci.domain.model.Badge;
import edu.dosw.rideci.domain.model.Reputation;
import edu.dosw.rideci.domain.model.Vehicle;
import edu.dosw.rideci.domain.model.enums.ProfileType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequestDTO {
    private String id; //Viene del microservicio de user?
    private String name; //Viene del microservicio de user?
    private List<Vehicle> vehicles; // referenciado
    private Reputation calification;
    private ProfileType profileType;
    private List<Long> ratings; //referencia a ratings
    private List<Badge> badges;


    
}
