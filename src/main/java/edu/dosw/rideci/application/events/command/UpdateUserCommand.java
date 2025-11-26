package edu.dosw.rideci.application.events.command;


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
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserCommand {
    private Long id; //Viene del microservicio de user?
    private String name; //Viene del microservicio de user?
    private List<Vehicle> vehicles; // referenciado
    //Lista de calificaciones ? 
    private Reputation calification;
    private ProfileType profileType;
    private List<Long> ratings; //referencia a ratings
    private List<Badge> badges;
    

}