package edu.dosw.rideci.application.events.listener;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import edu.dosw.rideci.application.events.UserEvent;
import edu.dosw.rideci.application.port.in.profiles.CreateProfileUseCase;
import edu.dosw.rideci.domain.model.Profile;
import edu.dosw.rideci.domain.model.enums.ProfileType;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Component
@RequiredArgsConstructor
@Slf4j
public class UserUpdatedListener {

    private final CreateProfileUseCase createProfileUseCase;

    @RabbitListener(queues = "profile.sync.queue")
    public void handleUserUpdated(UserEvent event){
        System.out.println("User updated event received" + event);
        Profile userEvent = Profile.builder()
            .id(event.getUserId())
            .name(event.getName())
            .vehicles(List.of())
            .calification(null)
            .profileType(ProfileType.NOT_DEFINED)
            .ratings(List.of())
            .badges(List.of())
            .phoneNumber(event.getPhoneNumber())
            .build();
        createProfileUseCase.createInitialProfile(userEvent);  
    }
    
}
