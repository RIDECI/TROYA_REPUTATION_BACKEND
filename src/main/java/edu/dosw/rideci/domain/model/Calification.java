package edu.dosw.rideci.domain.model;

import java.util.HashMap;

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
public class Calification {
    private String comments;
    //Conviene manejar hashmap?
    private HashMap<Integer,Double> califications; //Integer seria la calificacion 1-5 y Double el peso para asi evitar que se sobre pase
}
