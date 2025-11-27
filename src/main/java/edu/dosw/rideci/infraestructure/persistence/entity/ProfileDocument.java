package edu.dosw.rideci.infraestructure.persistence.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import edu.dosw.rideci.domain.model.enums.ProfileType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "profiles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProfileDocument {
    @Id
    private Long id; //Viene del microservicio de user?
    private String name; //Viene del microservicio de user?
    private List<VehicleDocument> vehicles; // referenciado
    private ReputationDocument calification;
    private ProfileType profileType;
    private String phoneNumber;
    private List<Long> ratings; //referencia a ratings
    private List<BadgeDocument> badges;
    
}
