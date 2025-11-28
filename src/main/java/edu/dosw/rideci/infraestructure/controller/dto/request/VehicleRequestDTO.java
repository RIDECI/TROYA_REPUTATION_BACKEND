package edu.dosw.rideci.infraestructure.controller.dto.request;

import edu.dosw.rideci.domain.model.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequestDTO {
    private String vehiclePlate;
    private String color;
    private String carBrand;
    private VehicleType vehicleType;
    
}
