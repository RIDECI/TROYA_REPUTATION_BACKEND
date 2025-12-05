package edu.dosw.rideci.ControllerTests;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import edu.dosw.rideci.application.mapper.InitialProfileMapper;
import edu.dosw.rideci.application.port.in.profiles.*;
import edu.dosw.rideci.application.port.in.rating.*;
import edu.dosw.rideci.domain.model.*;
import edu.dosw.rideci.domain.model.enums.ProfileType;
import edu.dosw.rideci.infraestructure.controller.ProfileController;
import edu.dosw.rideci.infraestructure.controller.dto.request.ProfileRequestDTO;
import edu.dosw.rideci.infraestructure.controller.dto.request.VehicleRequestDTO;
import edu.dosw.rideci.infraestructure.controller.dto.response.ProfileResponseDTO;
import edu.dosw.rideci.infraestructure.controller.dto.response.RatingResponseDTO;
import edu.dosw.rideci.infraestructure.controller.dto.response.BadgeResponse;

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

    @Mock private InitialProfileMapper profileMapper;

    @Mock private CreateProfileUseCase createProfileUseCase;

    @Mock private GetProfileUseCase getProfileUseCase;

    @Mock private GetAllProfilesUseCase getAllProfilesUseCase;

    @Mock private UpdateProfileUseCase updateProfileUseCase;

    @Mock private UpdateVehiclesProfileUseCase updateVehiclesProfileUseCase;

    @Mock private DeleteProfileUseCase deleteProfileUseCase;

    @Mock private CalculateAverageReputationUseCase calculateAverageReputationUseCase;

    @Mock private GetFullReputationHistoryUseCase getFullReputationHistoryUseCase;

    @Mock private GetRatingUseCase getRatingUseCase;

    @Mock private ListAllCommentsUseCase listAllCommentsUseCase;

    @Mock private GetAllCommentsUseCase getAllCommentsUseCase;

    @Mock private GetCommentByIdUseCase getCommentByIdUseCase;

    @Mock private DeleteCommentsAdminUseCase deleteCommentsAdminUseCase;

    @Mock private GetUserBadgesUseCase getUserBadgesUseCase;

    @Mock private GetTripReputationDetailUseCase getTripReputationDetailUseCase;

    @Mock private edu.dosw.rideci.application.mapper.InitialRatingMapper ratingMapper;
    
    @Mock private AssignBadgeUseCase assignBadgeUseCase;

    @InjectMocks
    private ProfileController profileController;

    @Test
    void testCreatePassengerProfile() {
        Vehicle vehicle = new Vehicle();
        Reputation calification = new Reputation();

        ProfileRequestDTO requestDTO = ProfileRequestDTO.builder()
                .userId(123L)
                .name("John")
                .vehicles(List.of(vehicle))
                .calification(calification)
                .profileType(ProfileType.PASSENGER)
                .build();

        Profile profileDomain = Profile.builder()
                .userId(123L)
                .name("John")
                .vehicles(List.of(vehicle))
                .calification(calification)
                .profileType(ProfileType.PASSENGER)
                .build();

        Profile createdProfileDomain = Profile.builder()
                .userId(123L)
                .name("John")
                .vehicles(List.of(vehicle))
                .calification(calification)
                .profileType(ProfileType.PASSENGER)
                .build();

        ProfileResponseDTO responseDTO = ProfileResponseDTO.builder()
                .userId(createdProfileDomain.getUserId())
                .name(createdProfileDomain.getName())
                .vehicles(createdProfileDomain.getVehicles())
                .calification(createdProfileDomain.getCalification())
                .profileType(createdProfileDomain.getProfileType())
                .build();

        when(profileMapper.toDomain(requestDTO)).thenReturn(profileDomain);
        when(createProfileUseCase.createPassengerProfile(profileDomain)).thenReturn(createdProfileDomain);
        when(profileMapper.toResponse(createdProfileDomain)).thenReturn(responseDTO);

        ResponseEntity<ProfileResponseDTO> response =
                profileController.createPassengerProfile(requestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void testCreateCompaniantProfile() {
        Vehicle vehicle = new Vehicle();
        Reputation calification = new Reputation();

        ProfileRequestDTO requestDTO = ProfileRequestDTO.builder()
                .userId(123L)
                .name("John")
                .vehicles(List.of(vehicle))
                .calification(calification)
                .profileType(ProfileType.COMPANION)
                .build();

        Profile profileDomain = Profile.builder()
                .userId(123L)
                .name("John")
                .vehicles(List.of(vehicle))
                .calification(calification)
                .profileType(ProfileType.COMPANION)
                .build();

        Profile createdProfileDomain = Profile.builder()
                .userId(123L)
                .name("John")
                .vehicles(List.of(vehicle))
                .calification(calification)
                .profileType(ProfileType.COMPANION)
                .build();

        ProfileResponseDTO responseDTO = ProfileResponseDTO.builder()
                .userId(createdProfileDomain.getUserId())
                .name(createdProfileDomain.getName())
                .vehicles(createdProfileDomain.getVehicles())
                .calification(createdProfileDomain.getCalification())
                .profileType(createdProfileDomain.getProfileType())
                .build();

        when(profileMapper.toDomain(requestDTO)).thenReturn(profileDomain);
        when(createProfileUseCase.createCompaniantProfile(profileDomain)).thenReturn(createdProfileDomain);
        when(profileMapper.toResponse(createdProfileDomain)).thenReturn(responseDTO);

        ResponseEntity<ProfileResponseDTO> response =
                profileController.createCompaniantProfile(requestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void testCreateDriverProfile() {
        Vehicle vehicle = new Vehicle();
        Reputation calification = new Reputation();

        ProfileRequestDTO requestDTO = ProfileRequestDTO.builder()
                .userId(123L)
                .name("John")
                .vehicles(List.of(vehicle))
                .calification(calification)
                .profileType(ProfileType.DRIVER)
                .build();

        Profile profileDomain = Profile.builder()
                .userId(123L)
                .name("John")
                .vehicles(List.of(vehicle))
                .calification(calification)
                .profileType(ProfileType.DRIVER)
                .build();

        Profile createdProfileDomain = Profile.builder()
                .userId(123L)
                .name("John")
                .vehicles(List.of(vehicle))
                .calification(calification)
                .profileType(ProfileType.DRIVER)
                .build();

        ProfileResponseDTO responseDTO = ProfileResponseDTO.builder()
                .userId(createdProfileDomain.getUserId())
                .name(createdProfileDomain.getName())
                .vehicles(createdProfileDomain.getVehicles())
                .calification(createdProfileDomain.getCalification())
                .profileType(createdProfileDomain.getProfileType())
                .build();

        when(profileMapper.toDomain(requestDTO)).thenReturn(profileDomain);
        when(createProfileUseCase.createDriverProfile(profileDomain)).thenReturn(createdProfileDomain);
        when(profileMapper.toResponse(createdProfileDomain)).thenReturn(responseDTO);

        ResponseEntity<ProfileResponseDTO> response =
                profileController.createDriverProfile(requestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void testGetProfileById() {
        Long id = 123L;
        Vehicle vehicle = new Vehicle();
        Reputation calification = new Reputation();

        Profile profileDomain = Profile.builder()
                .userId(id)
                .name("John")
                .vehicles(List.of(vehicle))
                .calification(calification)
                .profileType(ProfileType.PASSENGER)
                .build();

        ProfileResponseDTO responseDTO = ProfileResponseDTO.builder()
                .userId(profileDomain.getUserId())
                .name(profileDomain.getName())
                .vehicles(profileDomain.getVehicles())
                .calification(profileDomain.getCalification())
                .profileType(profileDomain.getProfileType())
                .build();

        when(getProfileUseCase.getProfileById(id)).thenReturn(profileDomain);
        when(profileMapper.toResponse(profileDomain)).thenReturn(responseDTO);

        ResponseEntity<ProfileResponseDTO> response =
                profileController.getProfileById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void testGetAllprofiles() {
        Long id = 123L;
        Vehicle vehicle = new Vehicle();
        Reputation calification = new Reputation();

        Profile profileDomain1 = Profile.builder()
                .userId(id)
                .name("John")
                .vehicles(List.of(vehicle))
                .calification(calification)
                .profileType(ProfileType.PASSENGER)
                .build();

        Profile profileDomain2 = Profile.builder()
                .userId(id)
                .name("John 2")
                .vehicles(List.of(vehicle))
                .calification(calification)
                .profileType(ProfileType.DRIVER)
                .build();

        ProfileResponseDTO responseDTO1 = ProfileResponseDTO.builder()
                .userId(profileDomain1.getUserId())
                .name(profileDomain1.getName())
                .vehicles(profileDomain1.getVehicles())
                .calification(profileDomain1.getCalification())
                .profileType(profileDomain1.getProfileType())
                .build();

        ProfileResponseDTO responseDTO2 = ProfileResponseDTO.builder()
                .userId(profileDomain2.getUserId())
                .name(profileDomain2.getName())
                .vehicles(profileDomain2.getVehicles())
                .calification(profileDomain2.getCalification())
                .profileType(profileDomain2.getProfileType())
                .build();

        when(getAllProfilesUseCase.getAllProfiles()).thenReturn(List.of(profileDomain1, profileDomain2));
        when(profileMapper.toListResponse(List.of(profileDomain1, profileDomain2)))
                .thenReturn(List.of(responseDTO1, responseDTO2));

        ResponseEntity<List<ProfileResponseDTO>> response =
                profileController.getAllProfiles();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(responseDTO1, responseDTO2), response.getBody());
    }

    @Test
    void testUpdateProfile() {
        Long id = 123L;

        ProfileRequestDTO requestDTO = ProfileRequestDTO.builder()
                .name("Francisco2")
                .build();

        Profile updatedDomain = Profile.builder()
                .userId(id)
                .name("Francisco2")
                .build();

        ProfileResponseDTO responseDTO = ProfileResponseDTO.builder()
                .userId(id)
                .name("Francisco2")
                .build();

        when(updateProfileUseCase.updateProfile(id, requestDTO)).thenReturn(updatedDomain);
        when(profileMapper.toResponse(updatedDomain)).thenReturn(responseDTO);

        ResponseEntity<ProfileResponseDTO> response =
                profileController.updateProfile(id, requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void testUpdateVehiclesProfile() {
                Long id = 456L;

        VehicleRequestDTO vehicleRequest = new VehicleRequestDTO();
        List<VehicleRequestDTO> requestList = List.of(vehicleRequest);

        Vehicle vehicleDomain = new Vehicle();

        Profile updatedDomain = Profile.builder()
        .userId(id)
                .vehicles(List.of(vehicleDomain))
                .build();

        ProfileResponseDTO responseDTO = ProfileResponseDTO.builder()
                .userId(id)
                .build();

        when(updateVehiclesProfileUseCase.updateVehiclesProfile(id, requestList))
                .thenReturn(updatedDomain);

        when(profileMapper.toResponse(updatedDomain))
                .thenReturn(responseDTO);

        ResponseEntity<ProfileResponseDTO> response =
                profileController.updateVehiclesProfile(id, requestList);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void testDeleteProfileById() {
                Long id = 789L;

        doNothing().when(deleteProfileUseCase).deleteProfileById(id);

        ResponseEntity<Void> response =
                profileController.deleteProfileById(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetAverageReputation() {
                Long id = 1L;
        Double avg = 4.5;

        when(calculateAverageReputationUseCase.calculateAverageReputation(id))
                .thenReturn(avg);

        ResponseEntity<Double> response =
                profileController.getAverageReputation(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(avg, response.getBody());
    }

    @Test
    void testGetReputationHistory() {
                Long id = 2L;

        Rating rating = new Rating();
        List<Rating> domainList = List.of(rating);

        RatingResponseDTO ratingResponseDTO = new RatingResponseDTO();
        List<RatingResponseDTO> responseList = List.of(ratingResponseDTO);

        when(getFullReputationHistoryUseCase.getReputationHistory(id))
                .thenReturn(domainList);

        when(ratingMapper.toListResponse(domainList))
                .thenReturn(responseList);

        ResponseEntity<List<RatingResponseDTO>> response =
                profileController.getReputationHistory(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseList, response.getBody());
    }

    @Test
    void testGetRatingById() {
                Long ratingId = 11L;
        Rating rating = new Rating();

        when(getRatingUseCase.getRatingById(ratingId)).thenReturn(rating);

        ResponseEntity<Rating> response =
                profileController.getRatingById(ratingId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rating, response.getBody());
    }

    @Test
    void testListAllComments() {
                Long id = 3L;

        when(listAllCommentsUseCase.listAllComments(id))
                .thenReturn(List.of("comment1", "comment2"));

        ResponseEntity<List<String>> response =
                profileController.listAllComments(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of("comment1", "comment2"), response.getBody());
    }

    @Test
    void testGetAllCommentsDetail() {
                Long id = 4L;

        Rating rating = new Rating();
        RatingResponseDTO dto = RatingResponseDTO.builder().build();

        when(getAllCommentsUseCase.getAllCommentsByProfile(id))
                .thenReturn(List.of(rating));

        when(ratingMapper.toListResponse(List.of(rating)))
                .thenReturn(List.of(dto));

        ResponseEntity<List<RatingResponseDTO>> response =
                profileController.getAllComments(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(dto), response.getBody());
    }

    @Test
    void testGetCommentById() {
        String commentId = "5L";

        Rating rating = new Rating();
        RatingResponseDTO dto = RatingResponseDTO.builder().build();

        when(getCommentByIdUseCase.getCommentById(commentId))
                .thenReturn(rating);

        when(ratingMapper.toResponse(rating))
                .thenReturn(dto);

        ResponseEntity<RatingResponseDTO> response =
                profileController.getCommentById(commentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testDeleteCommentByIdAndDeleteAllComments() {
        String commentId = "6L";
                Long id = 7L;

        doNothing().when(deleteCommentsAdminUseCase).deleteComment(commentId);

        ResponseEntity<Void> response =
                profileController.deleteCommentById(commentId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

        doNothing().when(deleteCommentsAdminUseCase).deleteAllCommentsByProfile(id);

        ResponseEntity<Void> response2 =
                profileController.deleteAllComments(id);

        assertEquals(HttpStatus.NO_CONTENT, response2.getStatusCode());
        assertNull(response2.getBody());
    }

    @Test
    void testGetBadgesAndTripRatingsAndAssignBadges() {
        Long id = 8L;

        Badge badge = new Badge();
        BadgeResponse badgeResponse = BadgeResponse.builder().build();

        Rating rating = new Rating();
        RatingResponseDTO ratingDto = RatingResponseDTO.builder().build();

        when(getUserBadgesUseCase.getBadgesForUser(id))
                .thenReturn(List.of(badge));

        when(ratingMapper.toBadgeResponse(List.of(badge)))
                .thenReturn(List.of(badgeResponse));

        ResponseEntity<List<BadgeResponse>> respBadges =
                profileController.getBadges(id);

        assertEquals(HttpStatus.OK, respBadges.getStatusCode());
        assertEquals(List.of(badgeResponse), respBadges.getBody());

        Long tripId = 9L;

        when(getTripReputationDetailUseCase.getRatingsForTripId(tripId))
                .thenReturn(List.of(rating));

        when(ratingMapper.toListResponse(List.of(rating)))
                .thenReturn(List.of(ratingDto));

        ResponseEntity<List<RatingResponseDTO>> respTrip =
                profileController.getTripRatings(tripId);

        assertEquals(HttpStatus.OK, respTrip.getStatusCode());
        assertEquals(List.of(ratingDto), respTrip.getBody());

        Profile profWithBadges = Profile.builder().userId(id).build();
        ProfileResponseDTO profResp = ProfileResponseDTO.builder().userId(id).build();

        when(assignBadgeUseCase.assignBadge(id))
                .thenReturn(profWithBadges);

        when(profileMapper.toResponse(profWithBadges))
                .thenReturn(profResp);

        ResponseEntity<ProfileResponseDTO> respAssign =
                profileController.assignBadges(id);

        assertEquals(HttpStatus.OK, respAssign.getStatusCode());
        assertEquals(profResp, respAssign.getBody());
    }
}
