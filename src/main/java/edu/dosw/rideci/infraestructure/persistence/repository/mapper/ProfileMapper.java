package edu.dosw.rideci.infraestructure.persistence.repository.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import edu.dosw.rideci.domain.model.Profile;
import edu.dosw.rideci.domain.model.Vehicle;
import edu.dosw.rideci.infraestructure.persistence.entity.ProfileDocument;
import edu.dosw.rideci.infraestructure.persistence.entity.VehicleDocument;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileDocument toDocument(Profile profile);
    Profile toDomain(ProfileDocument profileDocument);
    List<Profile> toListDomain(List<ProfileDocument> profileDocuments);
    VehicleDocument toVehicleDocument(Vehicle vehicle);
    Vehicle toVehicleDomain(VehicleDocument vehicleDocument);
    List<VehicleDocument> toVehicleDocumentList(List<Vehicle> vehicles);
    List<Vehicle> toVehicleDomainList(List<VehicleDocument> vehicleDocuments);
}
    