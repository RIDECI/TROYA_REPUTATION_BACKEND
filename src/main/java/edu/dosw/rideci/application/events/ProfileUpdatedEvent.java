package edu.dosw.rideci.application.events;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import edu.dosw.rideci.application.port.in.profiles.UpdateProfileUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProfileUpdatedEvent {
    private final UpdateProfileUseCase updateProfileUseCase;

    @RabbitListener(queues = "profile.updated")
    public void handleProfileUpdated(){
        System.out.println("Profile updated event sended");

    }

}