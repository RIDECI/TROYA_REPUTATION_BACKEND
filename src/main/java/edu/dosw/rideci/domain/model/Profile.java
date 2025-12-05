package edu.dosw.rideci.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import edu.dosw.rideci.domain.model.enums.IdentificationType;
import edu.dosw.rideci.domain.model.enums.ProfileType;
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
public class Profile {
    
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
