package edu.dosw.rideci.infraestructure.persistence.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import edu.dosw.rideci.domain.model.Calification;
import edu.dosw.rideci.domain.model.Vehicle;
import edu.dosw.rideci.domain.model.enums.ProfileType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "profile")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProfileDocument {
    @Id
    private Long id; //Viene del microservicio de user?
    private String name; //Viene del microservicio de user?
    private List<Vehicle>vehicles;
    private Calification calification;
    private ProfileType profileType;
    
}
