package edu.dosw.rideci.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import edu.dosw.rideci.domain.model.Profile;
import edu.dosw.rideci.domain.model.Vehicle;
import edu.dosw.rideci.infraestructure.controller.dto.request.ProfileRequestDTO;
import edu.dosw.rideci.infraestructure.controller.dto.response.ProfileResponseDTO;
import edu.dosw.rideci.infraestructure.controller.dto.response.VehicleResponseDTO;

@Mapper(componentModel = "spring")
public interface InitialProfileMapper {
    
    ProfileResponseDTO toResponse(Profile profile);
    Profile toDomain(ProfileRequestDTO travelRequest);
    List<ProfileResponseDTO> toListResponse(List<Profile> profile);
    List<Profile> toListDomain(List<ProfileRequestDTO> profile);
    VehicleResponseDTO toVehicleResponse(Vehicle vehicle);
    
}
