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
public class Reputation {
    
    private HashMap<Integer, Double> weightedScores; 
    private double average; 
    private int totalRatings;


}

