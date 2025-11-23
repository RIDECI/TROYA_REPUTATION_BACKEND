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
    public static final String PROFILE_UPDATED = "rideci.profile.updated.queue";
    public static final String PROFILE_UPDATED_ROUTING_KEY = "profile.updated"; 


    @Bean
    public Queue profileUpdatedQueue() {
        return new Queue(PROFILE_UPDATED, true);
    }

    @Bean
    public Binding bindingProfileUpdated() {
        return BindingBuilder
                .bind(profileUpdatedQueue())
                .to(profileExchange())
                .with(PROFILE_UPDATED_ROUTING_KEY); 
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange profileExchange() {
        return new TopicExchange("profile.exchange", true, false);
    }



}   