package edu.dosw.rideci.ControllerTests;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import edu.dosw.rideci.application.mapper.InitialProfileMapper;
import edu.dosw.rideci.application.port.in.profiles.*;
import edu.dosw.rideci.application.port.in.profiles.CreateDriverProfileUseCase;
import edu.dosw.rideci.application.port.in.profiles.CreatePassengerProfileUseCase;
import edu.dosw.rideci.application.port.in.profiles.GetProfileUseCase;
import edu.dosw.rideci.application.port.in.rating.CalculateAverageReputationUseCase;
import edu.dosw.rideci.application.port.in.rating.GetFullReputationHistoryUseCase;
import edu.dosw.rideci.application.port.in.rating.GetRatingUseCase;
import edu.dosw.rideci.application.port.in.rating.ListAllCommentsUseCase;
import edu.dosw.rideci.application.port.in.rating.GetAllCommentsUseCase;
import edu.dosw.rideci.application.port.in.rating.GetCommentByIdUseCase;
import edu.dosw.rideci.application.port.in.rating.DeleteCommentsAdminUseCase;
import edu.dosw.rideci.application.port.in.rating.GetUserBadgesUseCase;
import edu.dosw.rideci.application.port.in.rating.GetTripReputationDetailUseCase;
import edu.dosw.rideci.domain.model.*;
import edu.dosw.rideci.domain.model.Profile;
import edu.dosw.rideci.domain.model.Vehicle;
import edu.dosw.rideci.domain.model.enums.ProfileType;
import edu.dosw.rideci.infraestructure.controller.ProfileController;
import edu.dosw.rideci.infraestructure.controller.dto.request.ProfileRequestDTO;
import edu.dosw.rideci.infraestructure.controller.dto.response.ProfileResponseDTO;
import edu.dosw.rideci.infraestructure.controller.dto.response.RatingResponseDTO;
import edu.dosw.rideci.infraestructure.controller.dto.response.BadgeResponse;
import edu.dosw.rideci.domain.model.Rating;
import edu.dosw.rideci.domain.model.Badge;
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
        @Mock
        private CalculateAverageReputationUseCase calculateAverageReputationUseCase;
        @Mock
        private GetFullReputationHistoryUseCase getFullReputationHistoryUseCase;
        @Mock
        private GetRatingUseCase getRatingUseCase;
        @Mock
        private ListAllCommentsUseCase listAllCommentsUseCase;
        @Mock
        private GetAllCommentsUseCase getAllCommentsUseCase;
        @Mock
        private GetCommentByIdUseCase getCommentByIdUseCase;
        @Mock
        private DeleteCommentsAdminUseCase deleteCommentsAdminUseCase;
        @Mock
        private GetUserBadgesUseCase getUserBadgesUseCase;
        @Mock
        private GetTripReputationDetailUseCase getTripReputationDetailUseCase;
        @Mock
        private edu.dosw.rideci.application.mapper.InitialRatingMapper ratingMapper;
        @Mock
        private AssignBadgeUseCase assignBadgeUseCase;

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

        @Test
        void testGetAverageReputation() {
                Long id = 1L;
                Double avg = 4.5;

                when(calculateAverageReputationUseCase.calculateAverageReputation(id)).thenReturn(avg);

                ResponseEntity<Double> response = profileController.getAverageReputation(id);

                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals(avg, response.getBody());

                verify(calculateAverageReputationUseCase).calculateAverageReputation(id);
        }

        @Test
        void testGetReputationHistory() {
                Long id = 2L;
                Rating rating = new Rating();

                when(getFullReputationHistoryUseCase.getReputationHistory(id)).thenReturn(List.of(rating));

                ResponseEntity<List<Rating>> response = profileController.getReputationHistory(id);

                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals(List.of(rating), response.getBody());

                verify(getFullReputationHistoryUseCase).getReputationHistory(id);
        }

        @Test
        void testGetRatingById() {
                Long ratingId = 11L;
                Rating rating = new Rating();

                when(getRatingUseCase.getRatingById(ratingId)).thenReturn(rating);

                ResponseEntity<Rating> response = profileController.getRatingById(ratingId);

                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals(rating, response.getBody());

                verify(getRatingUseCase).getRatingById(ratingId);
        }

        @Test
        void testListAllComments() {
                Long id = 3L;

                when(listAllCommentsUseCase.listAllComments(id)).thenReturn(List.of("comment1", "comment2"));

                ResponseEntity<List<String>> response = profileController.listAllComments(id);

                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals(List.of("comment1", "comment2"), response.getBody());

                verify(listAllCommentsUseCase).listAllComments(id);
        }

        @Test
        void testGetAllCommentsDetail() {
                Long id = 4L;
                Rating rating = new Rating();
                RatingResponseDTO dto = RatingResponseDTO.builder().build();

                when(getAllCommentsUseCase.getAllCommentsByProfile(id)).thenReturn(List.of(rating));
                when(ratingMapper.toListResponse(List.of(rating))).thenReturn(List.of(dto));

                ResponseEntity<List<RatingResponseDTO>> response = profileController.getAllComments(id);

                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals(List.of(dto), response.getBody());

                verify(getAllCommentsUseCase).getAllCommentsByProfile(id);
                verify(ratingMapper).toListResponse(List.of(rating));
        }

        @Test
        void testGetCommentById() {
                Long commentId = 5L;
                Rating rating = new Rating();
                RatingResponseDTO dto = RatingResponseDTO.builder().build();

                when(getCommentByIdUseCase.getCommentById(commentId)).thenReturn(rating);
                when(ratingMapper.toResponse(rating)).thenReturn(dto);

                ResponseEntity<RatingResponseDTO> response = profileController.getCommentById(commentId);

                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals(dto, response.getBody());

                verify(getCommentByIdUseCase).getCommentById(commentId);
                verify(ratingMapper).toResponse(rating);
        }

        @Test
        void testDeleteCommentByIdAndDeleteAllComments() {
                Long commentId = 6L;
                Long id = 7L;

                doNothing().when(deleteCommentsAdminUseCase).deleteComment(commentId);

                ResponseEntity<Void> response = profileController.deleteCommentById(commentId);
                assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
                assertNull(response.getBody());
                verify(deleteCommentsAdminUseCase).deleteComment(commentId);

                doNothing().when(deleteCommentsAdminUseCase).deleteAllCommentsByProfile(id);
                ResponseEntity<Void> response2 = profileController.deleteAllComments(id);
                assertEquals(HttpStatus.NO_CONTENT, response2.getStatusCode());
                assertNull(response2.getBody());
                verify(deleteCommentsAdminUseCase).deleteAllCommentsByProfile(id);
        }

        @Test
        void testGetBadgesAndTripRatingsAndAssignBadges() {
                Long id = 8L;
                Badge badge = new Badge();
                BadgeResponse badgeResponse = BadgeResponse.builder().build();
                Rating rating = new Rating();
                RatingResponseDTO ratingDto = RatingResponseDTO.builder().build();

                when(getUserBadgesUseCase.getBadgesForUser(id)).thenReturn(List.of(badge));
                when(ratingMapper.toBadgeResponse(List.of(badge))).thenReturn(List.of(badgeResponse));

                Long tripId = 9L;
                when(getTripReputationDetailUseCase.getRatingsForTripId(tripId)).thenReturn(List.of(rating));
                when(ratingMapper.toListResponse(List.of(rating))).thenReturn(List.of(ratingDto));

                Profile profWithBadges = Profile.builder().id(id).build();
                ProfileResponseDTO profResp = ProfileResponseDTO.builder().id(id).build();
                when(assignBadgeUseCase.assignBadge(id)).thenReturn(profWithBadges);
                when(profileMapper.toResponse(profWithBadges)).thenReturn(profResp);

                ResponseEntity<List<BadgeResponse>> respBadges = profileController.getBadges(id);
                assertEquals(HttpStatus.OK, respBadges.getStatusCode());
                assertEquals(List.of(badgeResponse), respBadges.getBody());

                ResponseEntity<List<RatingResponseDTO>> respTrip = profileController.getTripRatings(tripId);
                assertEquals(HttpStatus.OK, respTrip.getStatusCode());
                assertEquals(List.of(ratingDto), respTrip.getBody());

                ResponseEntity<ProfileResponseDTO> respAssign = profileController.assignBadges(id);
                assertEquals(HttpStatus.OK, respAssign.getStatusCode());
                assertEquals(profResp, respAssign.getBody());

                verify(getUserBadgesUseCase).getBadgesForUser(id);
                verify(ratingMapper).toBadgeResponse(List.of(badge));
                verify(getTripReputationDetailUseCase).getRatingsForTripId(tripId);
                verify(ratingMapper).toListResponse(List.of(rating));
                verify(assignBadgeUseCase).assignBadge(id);
                verify(profileMapper).toResponse(profWithBadges);
        }

}
