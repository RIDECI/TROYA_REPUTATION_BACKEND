package edu.dosw.rideci.RepositoryAdapterTest;

import edu.dosw.rideci.application.mapper.InitialProfileMapper;
import edu.dosw.rideci.application.port.out.PortProfileRepository;
import edu.dosw.rideci.application.service.ProfileService;
import edu.dosw.rideci.domain.model.Profile;
import edu.dosw.rideci.domain.model.Vehicle;
import edu.dosw.rideci.infraestructure.controller.dto.request.ProfileRequestDTO;
import edu.dosw.rideci.infraestructure.controller.dto.request.VehicleRequestDTO;
import edu.dosw.rideci.exceptions.ProfileNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProfileRepositoryAdapterTest {

    @Mock
    private PortProfileRepository portProfileRepository;

    @Mock
    private InitialProfileMapper profileMapper;

    @InjectMocks
    private ProfileService profileService;

    @Test
    void TestCreateDriverProfile() {
        Profile profile = new Profile();
        Profile savedProfile = new Profile();

        when(portProfileRepository.createDriverProfile(profile)).thenReturn(savedProfile);

        Profile result = profileService.createDriverProfile(profile);

        assertEquals(savedProfile, result);
        verify(portProfileRepository).createDriverProfile(profile);
    }

    @Test
    void TestCreatePassengerProfile_shouldCallRepository() {
        Profile profile = new Profile();
        Profile savedProfile = new Profile();

        when(portProfileRepository.createPassengerProfile(profile)).thenReturn(savedProfile);

        Profile result = profileService.createPassengerProfile(profile);

        assertEquals(savedProfile, result);
        verify(portProfileRepository).createPassengerProfile(profile);
    }

    @Test
    void TestCreateCompaniantProfile() {
        Profile profile = new Profile();
        Profile savedProfile = new Profile();

        when(portProfileRepository.createCompaniantProfile(profile)).thenReturn(savedProfile);

        Profile result = profileService.createCompaniantProfile(profile);

        assertEquals(savedProfile, result);
        verify(portProfileRepository).createCompaniantProfile(profile);
    }

    @Test
    void TestCreateInitialProfile() {
        Profile profile = new Profile();
        Profile savedProfile = new Profile();

        when(portProfileRepository.createInitialProfile(profile)).thenReturn(savedProfile);

        Profile result = profileService.createInitialProfile(profile);

        assertEquals(savedProfile, result);
        verify(portProfileRepository).createInitialProfile(profile);
    }

    @Test
    void TestUpdateProfile_success() {
        Long id = 1L;

        ProfileRequestDTO dto = new ProfileRequestDTO();
        Profile domainProfile = new Profile();
        Profile updatedProfile = new Profile();

        when(profileMapper.toDomain(dto)).thenReturn(domainProfile);
        when(portProfileRepository.updateProfile(id, domainProfile)).thenReturn(updatedProfile);

        Profile result = profileService.updateProfile(id, dto);

        assertEquals(updatedProfile, result);
        verify(profileMapper).toDomain(dto);
        verify(portProfileRepository).updateProfile(id, domainProfile);
    }

    @Test
    void TestUpdateVehiclesProfile() {
        Long id = 2L;

        VehicleRequestDTO vehicleDto = new VehicleRequestDTO();
        List<VehicleRequestDTO> requestList = List.of(vehicleDto);

        Vehicle vehicleDomain = new Vehicle();
        List<Vehicle> vehicleListDomain = List.of(vehicleDomain);

        Profile updatedProfile = new Profile();

        when(profileMapper.toVehicleListDomain(requestList)).thenReturn(vehicleListDomain);
        when(portProfileRepository.updateVehiclesProfile(eq(id), any(Profile.class))).thenReturn(updatedProfile);

        Profile result = profileService.updateVehiclesProfile(id, requestList);

        assertEquals(updatedProfile, result);
        verify(profileMapper).toVehicleListDomain(requestList);
        verify(portProfileRepository).updateVehiclesProfile(eq(id), any(Profile.class));
    }

    @Test
    void TestUpdateVehiclesProfile_whenNotFound_throwsProfileNotFoundException() {
        Long id = 500L;

        VehicleRequestDTO vehicleDto = new VehicleRequestDTO();
        List<VehicleRequestDTO> requestList = List.of(vehicleDto);

        Vehicle vehicleDomain = new Vehicle();
        List<Vehicle> vehicleListDomain = List.of(vehicleDomain);

        when(profileMapper.toVehicleListDomain(requestList)).thenReturn(vehicleListDomain);
        when(portProfileRepository.updateVehiclesProfile(eq(id), any(Profile.class)))
                .thenThrow(new ProfileNotFoundException("Profile not found"));

        assertThrows(ProfileNotFoundException.class, () -> profileService.updateVehiclesProfile(id, requestList));

        verify(profileMapper).toVehicleListDomain(requestList);
        verify(portProfileRepository).updateVehiclesProfile(eq(id), any(Profile.class));
    }

    @Test
    void TestDeleteProfileById() {
        Long id = 2L;

        profileService.deleteProfileById(id);

        verify(portProfileRepository).deleteProfileById(id);
    }

    @Test
    void TestGetAllProfiles() {
        List<Profile> profiles = List.of(new Profile(), new Profile());

        when(portProfileRepository.getAllProfiles()).thenReturn(profiles);

        List<Profile> result = profileService.getAllProfiles();

        assertEquals(2, result.size());
        verify(portProfileRepository).getAllProfiles();
    }

    @Test
    void TestGetProfileById() {
        Long id = 1L;
        Profile profile = new Profile();
        profile.setUserId(id);

        when(portProfileRepository.getProfileById(id)).thenReturn(profile);

        Profile result = profileService.getProfileById(id);

        assertNotNull(result);
        assertEquals(id, result.getUserId());
        verify(portProfileRepository).getProfileById(id);
    }

    @Test
    void TestGetProfileById_whenNotFound_throwsProfileNotFoundException() {
        Long id = 100L;

        when(portProfileRepository.getProfileById(id))
                .thenThrow(new ProfileNotFoundException("Profile not found"));

        assertThrows(ProfileNotFoundException.class, () -> profileService.getProfileById(id));
        verify(portProfileRepository).getProfileById(id);
    }

    @Test
    void TestAssignBadge_whenProfileNotFound_throwsProfileNotFoundException() {
        Long id = 200L;

        when(portProfileRepository.getProfileById(id))
                .thenThrow(new ProfileNotFoundException("Profile not found"));

        assertThrows(ProfileNotFoundException.class, () -> profileService.assignBadge(id));
        verify(portProfileRepository).getProfileById(id);
    }

    @Test
    void TestDeleteProfileById_whenNotFound_throwsProfileNotFoundException() {
        Long id = 300L;

        doThrow(new ProfileNotFoundException("Profile not found"))
                .when(portProfileRepository).deleteProfileById(id);

        assertThrows(ProfileNotFoundException.class, () -> profileService.deleteProfileById(id));
        verify(portProfileRepository).deleteProfileById(id);
    }

    @Test
    void TestUpdateProfile_whenNotFound_throwsProfileNotFoundException() {
        Long id = 400L;

        ProfileRequestDTO dto = new ProfileRequestDTO();
        Profile domainProfile = new Profile();

        when(profileMapper.toDomain(dto)).thenReturn(domainProfile);
        when(portProfileRepository.updateProfile(id, domainProfile))
                .thenThrow(new ProfileNotFoundException("Profile not found"));

        assertThrows(ProfileNotFoundException.class, () -> profileService.updateProfile(id, dto));

        verify(profileMapper).toDomain(dto);
        verify(portProfileRepository).updateProfile(id, domainProfile);
    }
}
