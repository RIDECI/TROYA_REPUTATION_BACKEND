package edu.dosw.rideci.domain.model;

import edu.dosw.rideci.domain.model.enums.VehicleType;
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
public class Vehicle {

    private String vehiclePlate;
    private String color;
    private VehicleType vehicleType;
    private String vehicleModel;
    private String vehiclePhoto;
    
}