package edu.dosw.rideci.application.events.listener;

import edu.dosw.rideci.application.events.UserEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener {

    @RabbitListener(queues = "user.sync.queue")
    public void handleUserEvent(UserEvent event) {
        System.out.println("👤 Usuario recibido - ID: " + event.getUserId() + " | Email: " + event.getEmail());

        if ("ACTIVE".equals(event.getState())) {
            // Aquí puedes sincronizar el perfil o inicializar reputación
            System.out.println("Usuario activo: " + event.getName());
        }
    }
}
