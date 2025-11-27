package edu.dosw.rideci.infraestructure.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.dosw.rideci.application.mapper.InitialProfileMapper;
import edu.dosw.rideci.application.mapper.InitialRatingMapper;
import edu.dosw.rideci.application.port.in.profiles.AssignBadgeUseCase;
import edu.dosw.rideci.application.port.in.profiles.CreateProfileUseCase;
import edu.dosw.rideci.application.port.in.profiles.DeleteProfileUseCase;
import edu.dosw.rideci.application.port.in.profiles.GetAllProfilesUseCase;
import edu.dosw.rideci.application.port.in.profiles.GetProfileUseCase;
import edu.dosw.rideci.application.port.in.profiles.UpdateProfileUseCase;
import edu.dosw.rideci.application.port.in.profiles.UpdateVehiclesProfileUseCase;
import edu.dosw.rideci.application.port.in.rating.CalculateAverageReputationUseCase;
import edu.dosw.rideci.application.port.in.rating.DeleteCommentsAdminUseCase;
import edu.dosw.rideci.application.port.in.rating.GetAllCommentsUseCase;
import edu.dosw.rideci.application.port.in.rating.GetCommentByIdUseCase;
import edu.dosw.rideci.application.port.in.rating.GetFullReputationHistoryUseCase;
import edu.dosw.rideci.application.port.in.rating.GetRatingUseCase;
import edu.dosw.rideci.application.port.in.rating.GetTripReputationDetailUseCase;
import edu.dosw.rideci.application.port.in.rating.GetUserBadgesUseCase;
import edu.dosw.rideci.application.port.in.rating.ListAllCommentsUseCase;
import edu.dosw.rideci.domain.model.Profile;
import edu.dosw.rideci.domain.model.Rating;
import edu.dosw.rideci.infraestructure.controller.dto.request.ProfileRequestDTO;
import edu.dosw.rideci.infraestructure.controller.dto.response.BadgeResponse;
import edu.dosw.rideci.infraestructure.controller.dto.response.ProfileResponseDTO;
import edu.dosw.rideci.infraestructure.controller.dto.response.RatingResponseDTO;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor 

public class ProfileController{

    private final CreateProfileUseCase createProfileUseCase;

    private final GetProfileUseCase getProfileUseCase;

    private final GetAllProfilesUseCase getAllProfilesUseCase;

    private final UpdateProfileUseCase updateProfileUseCase;

    private final UpdateVehiclesProfileUseCase updateVehiclesProfileUseCase;

    private final DeleteProfileUseCase deleteProfileUseCase;

    private final InitialProfileMapper profileMapper;

    private final CalculateAverageReputationUseCase calculateAverageReputationUseCase;

    private final DeleteCommentsAdminUseCase deleteCommentsAdminUseCase;

    private final GetAllCommentsUseCase getAllCommentsUseCase;

    private final GetCommentByIdUseCase getCommentByIdUseCase;

    private final GetFullReputationHistoryUseCase getFullReputationHistoryUseCase;

    private final GetRatingUseCase getRatingUseCase;

    private final GetTripReputationDetailUseCase getTripReputationDetailUseCase;

    private final GetUserBadgesUseCase getUserBadgesUseCase;

    private final ListAllCommentsUseCase listAllCommentsUseCase;
    
    private final AssignBadgeUseCase assignBadgeUseCase;

    private final InitialRatingMapper ratingMapper;
    

    @PostMapping("/driver")
    public ResponseEntity<ProfileResponseDTO> createDriverProfile(@RequestBody ProfileRequestDTO profileRequest){
        Profile profile = profileMapper.toDomain(profileRequest);
        ProfileResponseDTO createdProfile = profileMapper.toResponse(createProfileUseCase.createDriverProfile(profile));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfile);
            
    }

    @PostMapping("/companiant")
    public ResponseEntity<ProfileResponseDTO> createCompaniantProfile(@RequestBody ProfileRequestDTO profileRequest){
        Profile profile = profileMapper.toDomain(profileRequest);
        ProfileResponseDTO createdProfile = profileMapper.toResponse(createProfileUseCase.createCompaniantProfile(profile));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfile);
            
    }

    @PostMapping("/passenger")
    public ResponseEntity<ProfileResponseDTO> createPassengerProfile(@RequestBody ProfileRequestDTO profileRequest){
        Profile profile = profileMapper.toDomain(profileRequest);
        ProfileResponseDTO createdProfile = profileMapper.toResponse(createProfileUseCase.createPassengerProfile(profile));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfile);
            
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> getProfileById(@PathVariable Long id) {

        ProfileResponseDTO profile = profileMapper.toResponse(getProfileUseCase.getProfileById(id));

        return ResponseEntity.ok(profile);
    }

    @GetMapping("/allProfiles")
    public ResponseEntity<List<ProfileResponseDTO>> getAllProfiles() {

        List<Profile> profile = getAllProfilesUseCase.getAllProfiles();

        List<ProfileResponseDTO> profileResponse = profileMapper.toListResponse(profile);

        return ResponseEntity.ok(profileResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> updateProfile(@PathVariable Long id,
            @RequestBody ProfileRequestDTO profileRequest) {

        ProfileResponseDTO updatedProfile = profileMapper.toResponse(updateProfileUseCase.updateProfile(id, profileRequest));

        return ResponseEntity.ok(updatedProfile);
    }

    @PutMapping("/{id}/vehicles")
    public ResponseEntity<ProfileResponseDTO> updateVehiclesProfile(@PathVariable Long id,
            @RequestBody ProfileRequestDTO profileRequest) {

        ProfileResponseDTO updatedProfile = profileMapper.toResponse(updateVehiclesProfileUseCase.updateVehiclesProfile(id, profileRequest));

        return ResponseEntity.ok(updatedProfile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfileById(@PathVariable Long id) {
        deleteProfileUseCase.deleteProfileById(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}/reputation/average")
    public ResponseEntity<Double> getAverageReputation(@PathVariable Long id) {
        return ResponseEntity.ok(calculateAverageReputationUseCase.calculateAverageReputation(id));
    }

    @GetMapping("/{id}/reputation/history")
    public ResponseEntity<List<Rating>> getReputationHistory(@PathVariable Long id) {
        return ResponseEntity.ok(getFullReputationHistoryUseCase.getReputationHistory(id));
    }

    @GetMapping("/ratings/{ratingId}")
    public ResponseEntity<Rating> getRatingById(@PathVariable Long ratingId) {
        return ResponseEntity.ok(getRatingUseCase.getRatingById(ratingId));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<String>> listAllComments(@PathVariable Long id) {
        return ResponseEntity.ok(listAllCommentsUseCase.listAllComments(id));
    }

    @GetMapping("/{id}/comments/detail")
    public ResponseEntity<List<RatingResponseDTO>> getAllComments(@PathVariable Long id) {
        return ResponseEntity.ok(ratingMapper.toListResponse(getAllCommentsUseCase.getAllCommentsByProfile(id)));
    }


    @GetMapping("/comments/{commentId}")
    public ResponseEntity<RatingResponseDTO> getCommentById(@PathVariable Long commentId) {
        return ResponseEntity.ok(ratingMapper.toResponse(getCommentByIdUseCase.getCommentById(commentId)));
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable Long commentId) {
        deleteCommentsAdminUseCase.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/comments")
    public ResponseEntity<Void> deleteAllComments(@PathVariable Long id) {
        deleteCommentsAdminUseCase.deleteAllCommentsByProfile(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/badges")
    public ResponseEntity<List<BadgeResponse>> getBadges(@PathVariable Long id) {
        return ResponseEntity.ok(ratingMapper.toBadgeResponse(getUserBadgesUseCase.getBadgesForUser(id)));
    }

    @GetMapping("/trip/{tripId}/ratings")
    public ResponseEntity<List<RatingResponseDTO>> getTripRatings(@PathVariable Long tripId) {
        return ResponseEntity.ok(ratingMapper.toListResponse(getTripReputationDetailUseCase.getRatingsForTripId(tripId)));
    }

    @PostMapping("/{id}/badges/calculate")
    public ResponseEntity<ProfileResponseDTO> assignBadges(@PathVariable Long id) {
        
        Profile profileWithBadges = assignBadgeUseCase.assignBadge(id);
        
        ProfileResponseDTO response = profileMapper.toResponse(profileWithBadges);

        return ResponseEntity.ok(response);
    }
    






}