package edu.dosw.rideci.infraestructure.persistence.entity;

import edu.dosw.rideci.domain.model.enums.VehicleType;


import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class VehicleDocument{

    private String vehiclePlate;
    private String color;
    private VehicleType vehicleType;
    private String vehicleModel;
    private String vehiclePhoto;
}