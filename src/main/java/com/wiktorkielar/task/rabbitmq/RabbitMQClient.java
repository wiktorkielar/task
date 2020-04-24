package com.wiktorkielar.task.rabbitmq;

import com.wiktorkielar.task.model.ColorEvent;
import io.micronaut.configuration.rabbitmq.annotation.Binding;
import io.micronaut.configuration.rabbitmq.annotation.RabbitClient;

@RabbitClient("colorsExchange")
public interface RabbitMQClient {

    @Binding("colorsRoutingKey")
    void updateColors(ColorEvent colorEvent);
}
