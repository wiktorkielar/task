package com.wiktorkielar.task.util;

import com.wiktorkielar.task.model.ColorEvent;
import com.wiktorkielar.task.model.ColorRequest;
import io.micronaut.context.annotation.Value;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class ColorMapper {

    private static final String COLOR_RED_NAME = "RED";
    private static final String COLOR_GREEN_NAME = "GREEN";
    private static final String COLOR_BLUE_NAME = "BLUE";

    @Value("${mapping.red}")
    private String colorRedRgb;

    @Value("${mapping.green}")
    private String colorGreenRgb;

    @Value("${mapping.blue}")
    private String colorBlueRgb;

    void setColorRedRgb(String colorRedRgb) {
        this.colorRedRgb = colorRedRgb;
    }

    void setColorGreenRgb(String colorGreenRgb) {
        this.colorGreenRgb = colorGreenRgb;
    }

    void setColorBlueRgb(String colorBlueRgb) {
        this.colorBlueRgb = colorBlueRgb;
    }

    public List<ColorEvent> getColorEvents(List<ColorRequest> colorRequests) {
        return colorRequests.stream()
                .map(this::getColorEvent)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    Optional<ColorEvent> getColorEvent(ColorRequest colorRequest) {

        return getColors().entrySet().stream()
                .filter(entry -> entry.getKey().equals(colorRequest.getColor()))
                .findFirst()
                .map(entry -> new ColorEvent(entry.getValue()));
    }

    private Map<String, String> getColors() {

        Map<String, String> colorsMap = new HashMap<>();

        colorsMap.put(colorRedRgb, COLOR_RED_NAME);
        colorsMap.put(colorGreenRgb, COLOR_GREEN_NAME);
        colorsMap.put(colorBlueRgb, COLOR_BLUE_NAME);

        return colorsMap;
    }
}
