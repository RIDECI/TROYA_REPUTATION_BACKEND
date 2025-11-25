package edu.dosw.rideci.ControllerTests;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import edu.dosw.rideci.application.mapper.InitialProfileMapper;
import edu.dosw.rideci.application.port.in.profiles.*;
import edu.dosw.rideci.application.port.in.profiles.CreateDriverProfileUseCase;
import edu.dosw.rideci.application.port.in.profiles.CreatePassengerProfileUseCase;
import edu.dosw.rideci.application.port.in.profiles.GetProfileUseCase;
import edu.dosw.rideci.domain.model.*;
import edu.dosw.rideci.domain.model.Profile;
import edu.dosw.rideci.domain.model.Vehicle;
import edu.dosw.rideci.domain.model.enums.ProfileType;
import edu.dosw.rideci.infraestructure.controller.ProfileController;
import edu.dosw.rideci.infraestructure.controller.dto.request.ProfileRequestDTO;
import edu.dosw.rideci.infraestructure.controller.dto.response.ProfileResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProfileControllerTest {
    @Mock
    private InitialProfileMapper profileMapper;

    @Mock
    private CreatePassengerProfileUseCase createPassengerProfileUseCase;
    @Mock
    private CreateDriverProfileUseCase createDriverProfileUseCase;
    @Mock
    private CreateCompaniantProfileUseCase createCompaniantProfileUseCase;
    @Mock
    private GetProfileUseCase getProfileUseCase;
    @Mock
    private GetAllProfilesUseCase getAllProfilesUseCase;
    @Mock
    private UpdateProfileUseCase updateProfileUseCase;
    @Mock
    private UpdateVehiclesProfileUseCase updateVehiclesProfileUseCase;
    @Mock
    private DeleteProfileUseCase deleteProfileUseCase;

    @InjectMocks
    private ProfileController profileController;



    @Test
    void testCreatePassengerProfile() {

        Vehicle vehicle = new Vehicle();
        Reputation calification = new Reputation();

        ProfileRequestDTO requestDTO = ProfileRequestDTO.builder()
                .id((Long )123L)
                .name("John")
                .vehicles(List.of(vehicle))
                .calification(calification)
                .profileType(ProfileType.PASSENGER)
                .build();


        Profile profileDomain = Profile.builder()
                .id((Long )123L)
                .name("John")
                .vehicles(List.of(vehicle))
                .calification(calification)
                .profileType(ProfileType.PASSENGER)
                .build();

        Profile createdProfileDomain = Profile.builder()
                .id((Long )123L)
                .name("John")
                .vehicles(List.of(vehicle))
                .calification(calification)
                .profileType(ProfileType.PASSENGER)
                .build();

        ProfileResponseDTO responseDTO = ProfileResponseDTO.builder()
                .id(createdProfileDomain.getId())
                .name(createdProfileDomain.getName())
                .vehicles(createdProfileDomain.getVehicles())
                .calification(createdProfileDomain.getCalification())
                .profileType(createdProfileDomain.getProfileType())
                .build();


        when(profileMapper.toDomain(requestDTO)).thenReturn(profileDomain);
        when(createPassengerProfileUseCase.createPassengerProfile(profileDomain)).thenReturn(createdProfileDomain);
        when(profileMapper.toResponse(createdProfileDomain)).thenReturn(responseDTO);

        ResponseEntity<ProfileResponseDTO> response = profileController.createPassengerProfile(requestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());

        verify(profileMapper).toDomain(requestDTO);
        verify(createPassengerProfileUseCase).createPassengerProfile(profileDomain);
        verify(profileMapper).toResponse(createdProfileDomain);
    }

    @Test
    void testCreateCompaniantProfile() {

        Vehicle vehicle = new Vehicle();
        Reputation calification = new Reputation();

        ProfileRequestDTO requestDTO = ProfileRequestDTO.builder()
                .id((Long )123L)
                .name("John")
                .vehicles(List.of(vehicle))
                .calification(calification)
                .profileType(ProfileType.COMPANION)
                .build();


        Profile profileDomain = Profile.builder()
                .id((Long )123L)
                .name("John")
                .vehicles(List.of(vehicle))
                .calification(calification)
                .profileType(ProfileType.COMPANION)
                .build();

        Profile createdProfileDomain = Profile.builder()
                .id((Long )123L)
                .name("John")
                .vehicles(List.of(vehicle))
                .calification(calification)
                .profileType(ProfileType.COMPANION)
                .build();

        ProfileResponseDTO responseDTO = ProfileResponseDTO.builder()
                .id(createdProfileDomain.getId())
                .name(createdProfileDomain.getName())
                .vehicles(createdProfileDomain.getVehicles())
                .calification(createdProfileDomain.getCalification())
                .profileType(createdProfileDomain.getProfileType())
                .build();


        when(profileMapper.toDomain(requestDTO)).thenReturn(profileDomain);
        when(createCompaniantProfileUseCase.createCompaniantProfile(profileDomain)).thenReturn(createdProfileDomain);
        when(profileMapper.toResponse(createdProfileDomain)).thenReturn(responseDTO);

        ResponseEntity<ProfileResponseDTO> response = profileController.createCompaniantProfile(requestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());

        verify(profileMapper).toDomain(requestDTO);
        verify(createCompaniantProfileUseCase).createCompaniantProfile(profileDomain);
        verify(profileMapper).toResponse(createdProfileDomain);
    }


    @Test
    void testCreateDriverProfile() {

        Vehicle vehicle = new Vehicle();
        Reputation calification = new Reputation();

        ProfileRequestDTO requestDTO = ProfileRequestDTO.builder()
                .id((Long )123L)
                .name("John")
                .vehicles(List.of(vehicle))
                .calification(calification)
                .profileType(ProfileType.DRIVER)
                .build();


        Profile profileDomain = Profile.builder()
                .id((Long )123L)
                .name("John")
                .vehicles(List.of(vehicle))
                .calification(calification)
                .profileType(ProfileType.DRIVER)
                .build();

        Profile createdProfileDomain = Profile.builder()
                .id((Long )123L)
                .name("John")
                .vehicles(List.of(vehicle))
                .calification(calification)
                .profileType(ProfileType.DRIVER)
                .build();

        ProfileResponseDTO responseDTO = ProfileResponseDTO.builder()
                .id(createdProfileDomain.getId())
                .name(createdProfileDomain.getName())
                .vehicles(createdProfileDomain.getVehicles())
                .calification(createdProfileDomain.getCalification())
                .profileType(createdProfileDomain.getProfileType())
                .build();


        when(profileMapper.toDomain(requestDTO)).thenReturn(profileDomain);
        when(createDriverProfileUseCase.createDriverProfile(profileDomain)).thenReturn(createdProfileDomain);
        when(profileMapper.toResponse(createdProfileDomain)).thenReturn(responseDTO);

        ResponseEntity<ProfileResponseDTO> response = profileController.createDriverProfile(requestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());

        verify(profileMapper).toDomain(requestDTO);
        verify(createDriverProfileUseCase).createDriverProfile(profileDomain);
        verify(profileMapper).toResponse(createdProfileDomain);
    }



    @Test
    void testGetProfileById() {

        Long id = (Long) 123L;

        Vehicle vehicle = new Vehicle();
        Reputation calification = new Reputation();

        Profile profileDomain = Profile.builder()
                .id(id)
                .name("John")
                .vehicles(List.of(vehicle))
                .calification(calification)
                .profileType(ProfileType.PASSENGER)
                .build();

        ProfileResponseDTO responseDTO = ProfileResponseDTO.builder()
                .id(profileDomain.getId())
                .name(profileDomain.getName())
                .vehicles(profileDomain.getVehicles())
                .calification(profileDomain.getCalification())
                .profileType(profileDomain.getProfileType())
                .build();

        when(getProfileUseCase.getProfileById(id)).thenReturn(profileDomain);
        when(profileMapper.toResponse(profileDomain)).thenReturn(responseDTO);

        ResponseEntity<ProfileResponseDTO> response = profileController.getProfileById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());

        verify(getProfileUseCase).getProfileById(id);
        verify(profileMapper).toResponse(profileDomain);
    }

    @Test
    void testGetAllprofiles() {
        Long id = (Long) 123L;
        Vehicle vehicle = new Vehicle();
        Reputation calification = new Reputation();

        Profile profileDomain1 = Profile.builder()
                .id(id)
                .name("John")
                .vehicles(List.of(vehicle))
                .calification(calification)
                .profileType(ProfileType.PASSENGER)
                .build();

        Profile profileDomain2 = Profile.builder()
                .id(id)
                .name("John 2")
                .vehicles(List.of(vehicle))
                .calification(calification)
                .profileType(ProfileType.DRIVER)
                .build();


        ProfileResponseDTO responseDTO1 = ProfileResponseDTO.builder()
                .id(profileDomain1.getId())
                .name(profileDomain1.getName())
                .vehicles(profileDomain1.getVehicles())
                .calification(profileDomain1.getCalification())
                .profileType(profileDomain1.getProfileType())
                .build();

        ProfileResponseDTO responseDTO2 = ProfileResponseDTO.builder()
                .id(profileDomain2.getId())
                .name(profileDomain2.getName())
                .vehicles(profileDomain2.getVehicles())
                .calification(profileDomain2.getCalification())
                .profileType(profileDomain2.getProfileType())
                .build();


        when(getAllProfilesUseCase.getAllProfiles()).thenReturn(List.of(profileDomain1, profileDomain2));
        when(profileMapper.toListResponse(List.of(profileDomain1, profileDomain2)))
                .thenReturn(List.of(responseDTO1, responseDTO2));

        ResponseEntity<List<ProfileResponseDTO>> response = profileController.getAllProfiles();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(responseDTO1,responseDTO2), response.getBody());

        verify(getAllProfilesUseCase).getAllProfiles();
        verify(profileMapper).toListResponse(List.of(profileDomain1, profileDomain2));

    }

    @Test
    void testUpdateProfile() {

        Long id = (Long) 123L;

        ProfileRequestDTO requestDTO = ProfileRequestDTO.builder()
                .name("Francisco2")
                .build();

        Profile updatedDomain = Profile.builder()
                .id(id)
                .name("Francisco2")
                .build();

        ProfileResponseDTO responseDTO = ProfileResponseDTO.builder()
                .id(id)
                .name("Francisco2")
                .build();

        when(updateProfileUseCase.updateProfile(id, requestDTO))
                .thenReturn(updatedDomain);
        when(profileMapper.toResponse(updatedDomain))
                .thenReturn(responseDTO);

        ResponseEntity<ProfileResponseDTO> response =
                profileController.updateProfile(id, requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());

        verify(updateProfileUseCase).updateProfile(id, requestDTO);
        verify(profileMapper).toResponse(updatedDomain);
    }

    @Test
    void testUpdateVehiclesProfile() {

        Long id = (Long) 456L;

        Vehicle vehicle = new Vehicle();
        ProfileRequestDTO requestDTO = ProfileRequestDTO.builder()
                .vehicles(List.of(vehicle))
                .build();

        Profile updatedDomain = Profile.builder()
                .id(id)
                .vehicles(List.of(vehicle))
                .build();

        ProfileResponseDTO responseDTO = ProfileResponseDTO.builder()
                .id(id)
                .vehicles(List.of(vehicle))
                .build();

        when(updateVehiclesProfileUseCase.updateVehiclesProfile(id, requestDTO))
                .thenReturn(updatedDomain);
        when(profileMapper.toResponse(updatedDomain))
                .thenReturn(responseDTO);

        ResponseEntity<ProfileResponseDTO> response =
                profileController.updateVehiclesProfile(id, requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());

        verify(updateVehiclesProfileUseCase).updateVehiclesProfile(id, requestDTO);
        verify(profileMapper).toResponse(updatedDomain);
    }

    @Test
    void testDeleteProfileById() {

        Long id = (Long) 789L;

        doNothing().when(deleteProfileUseCase).deleteProfileById(id);

        ResponseEntity<Void> response = profileController.deleteProfileById(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());


        verify(deleteProfileUseCase).deleteProfileById(id);
    }

}
