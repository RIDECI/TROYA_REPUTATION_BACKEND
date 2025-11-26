package edu.dosw.rideci.application.events.listener;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import edu.dosw.rideci.application.events.UserEvent;
import edu.dosw.rideci.application.port.in.profiles.CreateDriverProfileUseCase;
import edu.dosw.rideci.domain.model.Profile;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Component
@RequiredArgsConstructor
@Slf4j
public class UserUpdatedListener {

    private final CreateDriverProfileUseCase updateUserUseCase;

    @RabbitListener(queues = "profile.sync.queue")
    public void handleUserUpdated(UserEvent event){
        System.out.println("User updated event received" + event);
        Profile userEvent = Profile.builder()
            .id(event.getUserId())
            .name(event.getName())
            .vehicles(List.of())
            .calification(null)
            .profileType(null)
            .ratings(List.of())
            .badges(List.of())
            .phoneNumber(event.getPhoneNumber())
            .build();
        updateUserUseCase.createDriverProfile(userEvent);  
    }
    
}
