package edu.dosw.rideci.infraestructure.controller.dto.response;


import edu.dosw.rideci.domain.model.enums.VehicleType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleResponseDTO {
    private String vehiclePlate;
    private String color;
    private String carBrand;
    private VehicleType vehicleType;
    
}
