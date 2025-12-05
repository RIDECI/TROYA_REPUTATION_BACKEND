package edu.dosw.rideci.domain.model;

import java.time.LocalDateTime;

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
// Referenciado, otro documento
public class Rating {
    private Long ratingId; 
    private Long tripId;      // Para el historial de viajes
    private Long raterId;     // Quién califica
    private Long targetId;    // (Dueño del perfil) A quien se le califica
    private Integer score;    
    private String comment;
    private LocalDateTime date;
}


