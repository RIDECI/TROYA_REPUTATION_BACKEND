package edu.dosw.rideci.infraestructure.controller.dto.request;


import java.time.LocalDateTime;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingRequestDTO {

    @Id
    private Long id; 
    private Long tripId;      // Para el historial de viajes
    private Long raterId;     // Quién califica
    private Long targetId;    // (Dueño del perfil) A quien se le califica
    private Integer score;    
    private LocalDateTime date;
    private String comment;
    
}
