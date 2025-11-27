package edu.dosw.rideci.application.events.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import edu.dosw.rideci.application.events.TravelCompletedEvent;
import edu.dosw.rideci.application.port.in.profiles.CreateProfileUseCase;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Component
@RequiredArgsConstructor
@Slf4j
public class TravelFinishedListener {

    private final CreateProfileUseCase updateUserUseCase;

    @RabbitListener(queues = "rating.sync.queue")
    public void handleUserUpdated(TravelCompletedEvent event){
        System.out.println("User updated event received" + event);
        //pendiente
    
    }
}
