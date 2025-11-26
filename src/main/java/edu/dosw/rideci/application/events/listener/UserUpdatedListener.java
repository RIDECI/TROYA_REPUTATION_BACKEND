package edu.dosw.rideci.application.events.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import edu.dosw.rideci.application.events.command.UpdateUserCommand;
import edu.dosw.rideci.application.port.in.profiles.UpdateUserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserUpdatedListener {

    private final UpdateUserUseCase updateUserUseCase;

    @RabbitListener(queues = "profile.user.updated.queue")
    public void handleUserUpdated(UpdateUserCommand event){
        System.out.println("User updated event received" + event);
        UpdateUserCommand command = new UpdateUserCommand(
            event.getId(),
            event.getName(),
            event.getVehicles(),
            event.getCalification(),
            event.getProfileType(),
            event.getRatings(),
            event.getBadges());
        updateUserUseCase.updateUser(command);  
    }
    
}
