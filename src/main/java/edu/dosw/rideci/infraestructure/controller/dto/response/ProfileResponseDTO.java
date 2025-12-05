package edu.dosw.rideci.infraestructure.controller.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import edu.dosw.rideci.domain.model.Badge;
import edu.dosw.rideci.domain.model.Reputation;
import edu.dosw.rideci.domain.model.Vehicle;
import edu.dosw.rideci.domain.model.enums.IdentificationType;
import edu.dosw.rideci.domain.model.enums.ProfileType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponseDTO {
    private Long userId; //Viene del microservicio de user?
    private String name; //Viene del microservicio de user?
    private List<Vehicle> vehicles; // referenciado
    //Lista de calificaciones ? 
    private Reputation calification;
    private String phoneNumber;
    private ProfileType profileType;
    private List<Long> ratings; //referencia a ratings
    private List<Badge> badges;
    private String email;
    //private IdentificationType identificationType;
    private String identificationNumber;
    private String address;
    private LocalDateTime birthDate;
    private String profilePictureUrl;
    private IdentificationType identificationType;
    
}
