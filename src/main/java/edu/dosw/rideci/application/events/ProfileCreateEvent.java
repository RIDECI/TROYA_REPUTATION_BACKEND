package edu.dosw.rideci.application.events;

import java.util.List;

import edu.dosw.rideci.domain.model.Badge;
import edu.dosw.rideci.domain.model.Reputation;
import edu.dosw.rideci.domain.model.Vehicle;
import edu.dosw.rideci.domain.model.enums.ProfileType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileCreateEvent {
    private Long id; //Viene del microservicio de user?
    private String name; //Viene del microservicio de user?
    private List<Vehicle> vehicles; // referenciado
    private Reputation calification;
    private ProfileType profileType;
    private List<Long> ratings; //referencia a ratings
    private List<Badge> badges;

}