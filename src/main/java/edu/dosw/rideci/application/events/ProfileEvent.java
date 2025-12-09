package edu.dosw.rideci.application.events;


import java.time.LocalDateTime;
import java.util.List;

import edu.dosw.rideci.domain.model.Badge;
import edu.dosw.rideci.domain.model.Reputation;
import edu.dosw.rideci.domain.model.Vehicle;
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
public class ProfileEvent {
    
    private Long userId;
    private String name;
    private List<Vehicle> vehicles;
    private Reputation calification;
    private String phoneNumber;
    private ProfileType profileType;
    private List<Long> ratings;
    private List<Badge> badges;
    private String email;
    private String identificationNumber;
    private String address;
    private LocalDateTime birthDate;
    private String profilePictureUrl;
    private IdentificationType identificationType;
    

}
