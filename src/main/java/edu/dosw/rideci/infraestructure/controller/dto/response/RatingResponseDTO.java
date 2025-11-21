package edu.dosw.rideci.infraestructure.controller.dto.response;


import java.time.LocalDateTime;
import java.util.HashMap;

import edu.dosw.rideci.domain.model.Reputation;
import edu.dosw.rideci.domain.model.Vehicle;
import edu.dosw.rideci.domain.model.enums.ProfileType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RatingResponseDTO {

    @Id
    private Long id; 
    private Long tripId;      // Para el historial de viajes
    private Long raterId;     // Quién califica
    private Long targetId;    // (Dueño del perfil) A quien se le califica
    private Integer score;    
    private LocalDateTime date;
    private String comment;
    
}
