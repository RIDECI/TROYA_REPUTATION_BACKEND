package edu.dosw.rideci.application.events.listener;

import java.util.Collections;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import edu.dosw.rideci.application.events.UserEvent;
import edu.dosw.rideci.application.port.in.profiles.CreateProfileUseCase;
import edu.dosw.rideci.domain.model.Profile;
import edu.dosw.rideci.domain.model.enums.ProfileType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserUpdatedListener {

    private final CreateProfileUseCase createProfileUseCase;

    @RabbitListener(queues = "profile.sync.queue")
    public void handleUserUpdated(UserEvent event){
        log.info("User updated event received: {}", event);
        Profile userEvent = Profile.builder()
            .id(event.getUserId()) 
            .name(event.getName())
            .email(event.getEmail())
            .vehicles(Collections.emptyList())
            .calification(null) 
            .profileType(ProfileType.NOT_DEFINED)
            .ratings(Collections.emptyList())
            .badges(Collections.emptyList())
            .phoneNumber(event.getPhoneNumber())
            .identificationType(event.getIdentificationType())
            .build();
        createProfileUseCase.createInitialProfile(userEvent);  
    }
    
}