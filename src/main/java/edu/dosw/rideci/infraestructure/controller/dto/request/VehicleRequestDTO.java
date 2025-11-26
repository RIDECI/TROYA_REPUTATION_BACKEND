package edu.dosw.rideci.infraestructure.controller.dto.request;

import edu.dosw.rideci.domain.model.enums.VehicleType;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class VehicleRequestDTO {
    private String vehiclePlate;
    private String color;
    private String carBrand;
    private VehicleType vehicleType;
    
}
