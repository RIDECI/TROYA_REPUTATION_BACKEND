package edu.dosw.rideci.application.events.listener;

import edu.dosw.rideci.application.events.TravelCompletedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TravelEventListener {

    @RabbitListener(queues = "travel.completed.queue")
    public void handleTravelCompleted(TravelCompletedEvent event) {
        System.out.println("🚗 Viaje completado - ID: " + event.getTravelId() + " | Estado: " + event.getState());

        if ("COMPLETED".equals(event.getState())) {
            // Aquí puedes habilitar calificaciones para este viaje
            System.out.println("Habilitando calificaciones para viaje " + event.getTravelId());
        }
    }
}
