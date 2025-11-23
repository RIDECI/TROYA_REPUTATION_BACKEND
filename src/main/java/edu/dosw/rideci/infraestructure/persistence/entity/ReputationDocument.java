package edu.dosw.rideci.infraestructure.persistence.entity;

import java.util.HashMap;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReputationDocument{

    private HashMap<Integer, Double> weightedScores; 
    private double average; 
    private int totalRatings;

}