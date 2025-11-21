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

    @Bean
    public Queue profileCreatedQueue() {
        return new Queue("profile.created.queue", true);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange profileExchange() {
        return new TopicExchange("profile.exchange", true, false);
    }

    @Bean
    public Binding bindingProfileCreated() {
        return BindingBuilder.bind(profileCreatedQueue()).to(profileExchange()).with("profile.created");
    }

}