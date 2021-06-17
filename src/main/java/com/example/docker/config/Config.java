package com.example.docker.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Config {

    private static final String DOCKER = "docker";
    private static final String EXCHANGE_DOCKER = "exchange-docker";

    @Bean
    Queue queueCore() {
        return new Queue(DOCKER, true);
    }

    @Bean(EXCHANGE_DOCKER)
    TopicExchange exchangeRestaurantCore() {
        return new TopicExchange(EXCHANGE_DOCKER);
    }

    @Bean
    Binding bindingRestaurantCore(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("foo.docker");
    }

    @Bean
    SimpleMessageListenerContainer containerRestaurantCore(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(DOCKER);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean("docker-core")
    MessageListenerAdapter listenerRestaurantCore(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

}
