package edu.dosw.rideci.infraestructure.controller.dto.response;


import edu.dosw.rideci.domain.model.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponseDTO {
    private String vehiclePlate;
    private String color;
    private VehicleType vehicleType;
    private String vehicleModel;
    private String vehiclePhoto;
    
}
