package com.wiktorkielar.task.service;

import com.wiktorkielar.task.model.ColorEvent;
import com.wiktorkielar.task.model.ColorRequest;
import com.wiktorkielar.task.rabbitmq.RabbitMQClient;
import com.wiktorkielar.task.util.ColorFilter;
import com.wiktorkielar.task.util.ColorMapper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class ColorServiceImpl implements ColorService {

    @Inject
    private RabbitMQClient rabbitMQClient;

    @Inject
    private ColorMapper colorMapper;

    @Inject
    private ColorFilter colorFilter;

    @Override
    public void publish(List<ColorRequest> colorRequests) {

        List<ColorRequest> filteredColorRequests = colorFilter.getRequests(colorRequests);
        List<ColorEvent> colorEvents = colorMapper.getColorEvents(filteredColorRequests);
        colorEvents.stream().forEach(colorEvent -> rabbitMQClient.updateColors(colorEvent));
    }

}
