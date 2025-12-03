package edu.dosw.rideci.infraestructure.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class RabbitConfig {
    public static final String PROFILE_CREATED_QUEUE = "profile.sync.queue";
    public static final String EXCHANGE_PROFILE = "profile.exchange";
    public static final String PROFILE_CREATED_ROUTING_KEY = "profile.created";

    //------------------------------------
    //Conexion con UserManagement 
    //Recibe todo lo que empiece por user
    public static final String USER_CREATED_ROUTING_KEY = "user.#";
    //Punto donde enruta
    public static final String EXCHANGE_USER = "user.exchange";
    


    @Bean
    public Queue profileCreatedQueue() {
        return new Queue(PROFILE_CREATED_QUEUE, true);
    }

    //Exchange Para mi
    @Bean
    public TopicExchange profileExchange() {
        return new TopicExchange(EXCHANGE_PROFILE, true, false);
    }

    //Binding Para mi
    @Bean
    public Binding bindingProfileCreated(Queue profileCreatedQueue, TopicExchange profileExchange) {
        return BindingBuilder.bind(profileCreatedQueue).to(profileExchange).with(PROFILE_CREATED_QUEUE);
    }
    //-----------------------------------------------------------------
    //Conexion con microservicio de UserManagement
    @Bean
    public TopicExchange userExchange() {
        return new TopicExchange(EXCHANGE_USER, true, false);
    }

    @Bean
    public Queue userCreatedQueue() {
        return new Queue("user.sync.queue", true);
    }

    @Bean
    public Binding bindingUserCreated(Queue profileCreatedQueue, TopicExchange userExchange) {
        return BindingBuilder.bind(profileCreatedQueue).to(userExchange).with(USER_CREATED_ROUTING_KEY);
    }
    //-----------------------------------------------------------------


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }



}   