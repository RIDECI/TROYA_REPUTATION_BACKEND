package edu.dosw.rideci.TROYA_REPUTATION_BACKEND.ServicesTest;


import edu.dosw.rideci.application.mapper.InitialProfileMapper;
import edu.dosw.rideci.application.port.out.PortProfileRepository;
import edu.dosw.rideci.application.service.ProfileService;
import edu.dosw.rideci.domain.model.Profile;
import edu.dosw.rideci.infraestructure.controller.dto.request.ProfileRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {

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

        when(portProfileRepository.createDriverProfile(profile))
                .thenReturn(savedProfile);

        Profile result = profileService.createDriverProfile(profile);

        assertEquals(savedProfile, result);
        verify(portProfileRepository).createDriverProfile(profile);
    }

    @Test
    void TestCreatePassengerProfile_shouldCallRepository() {
        Profile profile = new Profile();
        Profile savedProfile = new Profile();

        when(portProfileRepository.createPassengerProfile(profile))
                .thenReturn(savedProfile);

        Profile result = profileService.createPassengerProfile(profile);

        assertEquals(savedProfile, result);
        verify(portProfileRepository).createPassengerProfile(profile);
    }


    @Test
    void TestCreateCompaniantProfile() {
        Profile profile = new Profile();
        Profile savedProfile = new Profile();

        when(portProfileRepository.createCompaniantProfile(profile))
                .thenReturn(savedProfile);

        Profile result = profileService.createCompaniantProfile(profile);

        assertEquals(savedProfile, result);
        verify(portProfileRepository).createCompaniantProfile(profile);
    }

//    @Test
//    void TestUpdateProfile() {
//        Long id = (Long) 1L;
//
//        ProfileRequestDTO dto = new ProfileRequestDTO();
//        Profile domainProfile = new Profile();
//        Profile updatedProfile = new Profile();
//
//        when(profileMapper.toDomain(dto)).thenReturn(domainProfile);
//        when(portProfileRepository.updateProfile(id, domainProfile))
//                .thenReturn(updatedProfile);
//
//        Profile result = profileService.updateProfile(id, dto);
//
//        assertEquals(updatedProfile, result);
//        verify(profileMapper).toDomain(dto);
//        verify(portProfileRepository).updateProfile(id, domainProfile);
//    }


//    @Test
//    void TestUpdateVehiclesProfile() {
//        Long id = (Long) 1L;
//
//        ProfileRequestDTO dto = new ProfileRequestDTO();
//        Profile domainProfile = new Profile();
//        Profile updatedProfile = new Profile();
//
//        when(profileMapper.toDomain(dto)).thenReturn(domainProfile);
//        when(portProfileRepository.updateVehiclesProfile(id, domainProfile))
//                .thenReturn(updatedProfile);
//
//        Profile result = profileService.updateVehiclesProfile(id, dto);
//
//        assertEquals(updatedProfile, result);
//        verify(profileMapper).toDomain(dto);
//        verify(portProfileRepository).updateVehiclesProfile(id, domainProfile);
//    }


    @Test
    void TestDeleteProfileById() {
        Long id = (Long) 2L;

        profileService.deleteProfileById(id);

        verify(portProfileRepository).deleteProfileById(id);
    }


    @Test
    void TestGetAllProfiles() {
        List<Profile> profiles = List.of(new Profile(), new Profile());

        when(portProfileRepository.getAllProfiles())
                .thenReturn(profiles);

        List<Profile> result = profileService.getAllProfiles();

        assertEquals(2, result.size());
        verify(portProfileRepository).getAllProfiles();
    }

    @Test
    void TestGetProfileById() {
        Long id = (Long) 1L;
        Profile profile = new Profile();
        profile.setId(id);

        when(portProfileRepository.getProfileById(id)).thenReturn(profile);

        Profile result = profileService.getProfileById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(portProfileRepository).getProfileById(id);
    }
}
