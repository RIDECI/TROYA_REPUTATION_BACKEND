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
import edu.dosw.rideci.application.port.in.CreateCompaniantProfileUseCase;
import edu.dosw.rideci.application.port.in.CreateDriverProfileUseCase;
import edu.dosw.rideci.application.port.in.CreatePassengerProfileUseCase;
import edu.dosw.rideci.application.port.in.DeleteProfileUseCase;
import edu.dosw.rideci.application.port.in.GetAllProfilesUseCase;
import edu.dosw.rideci.application.port.in.GetProfileUseCase;
import edu.dosw.rideci.application.port.in.UpdateProfileUseCase;
import edu.dosw.rideci.application.port.in.UpdateVehiclesProfileUseCase;
import edu.dosw.rideci.domain.model.Profile;
import edu.dosw.rideci.infraestructure.controller.dto.request.ProfileRequestDTO;
import edu.dosw.rideci.infraestructure.controller.dto.response.ProfileResponseDTO;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor 

public class ProfileController{
    private final CreateDriverProfileUseCase createDriverProfileUseCase;
    private final CreateCompaniantProfileUseCase createCompaniantProfileUseCase;
    private final CreatePassengerProfileUseCase createPassengerProfileUseCase;
    private final GetProfileUseCase getProfileUseCase;
    private final GetAllProfilesUseCase getAllProfilesUseCase;
    private final UpdateProfileUseCase updateProfileUseCase;
    private final UpdateVehiclesProfileUseCase updateVehiclesProfileUseCase;
    private final DeleteProfileUseCase deleteProfileUseCase;
    private final InitialProfileMapper profileMapper;

    @PostMapping("")
    public ResponseEntity<ProfileResponseDTO> createDriverProfile(@RequestBody ProfileRequestDTO profileRequest){
        Profile profile = profileMapper.toDomain(profileRequest);
        ProfileResponseDTO createdProfile = profileMapper.toResponse(createDriverProfileUseCase.createDriverProfile(profile));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfile);
            
    }

    @PostMapping("")
    public ResponseEntity<ProfileResponseDTO> createCompaniantProfile(@RequestBody ProfileRequestDTO profileRequest){
        Profile profile = profileMapper.toDomain(profileRequest);
        ProfileResponseDTO createdProfile = profileMapper.toResponse(createCompaniantProfileUseCase.createCompaniantProfile(profile));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfile);
            
    }

    @PostMapping("")
    public ResponseEntity<ProfileResponseDTO> createPassengerProfile(@RequestBody ProfileRequestDTO profileRequest){
        Profile profile = profileMapper.toDomain(profileRequest);
        ProfileResponseDTO createdProfile = profileMapper.toResponse(createPassengerProfileUseCase.createPassengerProfile(profile));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfile);
            
    }

    @GetMapping("{id}")
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

    @PutMapping("{id}")
    public ResponseEntity<ProfileResponseDTO> updateProfile(@PathVariable Long id,
            @RequestBody ProfileRequestDTO profileRequest) {

        ProfileResponseDTO updatedProfile = profileMapper.toResponse(updateProfileUseCase.updateProfile(id, profileRequest));

        return ResponseEntity.ok(updatedProfile);
    }

    @PutMapping("{id}/vehicles")
    public ResponseEntity<ProfileResponseDTO> updateVehiclesProfile(@PathVariable Long id,
            @RequestBody ProfileRequestDTO profileRequest) {

        ProfileResponseDTO updatedProfile = profileMapper.toResponse(updateVehiclesProfileUseCase.updateVehiclesProfile(id, profileRequest));

        return ResponseEntity.ok(updatedProfile);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProfileById(@PathVariable Long id) {
        deleteProfileUseCase.deleteProfileById(id);

        return ResponseEntity.noContent().build();

    }




}