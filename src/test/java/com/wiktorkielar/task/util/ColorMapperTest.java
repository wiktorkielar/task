package com.wiktorkielar.task.util;

import com.wiktorkielar.task.model.ColorEvent;
import com.wiktorkielar.task.model.ColorRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ColorMapperTest {

    private ColorMapper colorMapper;

    @BeforeEach
    public void setup() {
        colorMapper = new ColorMapper();
        colorMapper.setColorRedRgb("255,0,0");
        colorMapper.setColorGreenRgb("0,255,0");
        colorMapper.setColorBlueRgb("0,0,255");
    }

    @Test
    void getColorEvents_NonEmptyColorRequest_NonEmptyColorEventList() {

        List<ColorRequest> colorRequests = List.of(
                new ColorRequest(true, "255,0,0"),
                new ColorRequest(true, "0,255,0"),
                new ColorRequest(true, "0,0,255")
        );

        List<ColorEvent> expectedColorEvents = List.of(
                new ColorEvent("RED"),
                new ColorEvent("GREEN"),
                new ColorEvent("BLUE")
        );

        List<ColorEvent> actualColorEvents = colorMapper.getColorEvents(colorRequests);

        assertEquals(expectedColorEvents.get(0).getColor(), actualColorEvents.get(0).getColor());
        assertEquals(expectedColorEvents.get(1).getColor(), actualColorEvents.get(1).getColor());
        assertEquals(expectedColorEvents.get(2).getColor(), actualColorEvents.get(2).getColor());
    }

    @Test
    void getColorEvents_NonEmptyColorRequest_EmptyColorEventList() {

        List<ColorRequest> colorRequests = List.of();

        List<ColorEvent> actualColorEvents = colorMapper.getColorEvents(colorRequests);

        assertTrue(actualColorEvents.isEmpty());

    }

    @Test
    void getColorEvent_ValidColorRequest_NonEmptyOptional() {

        Optional<ColorEvent> expectedColorEventRed =
                Optional.of(new ColorEvent("RED"));
        Optional<ColorEvent> expectedColorEventGreen =
                Optional.of(new ColorEvent("GREEN"));
        Optional<ColorEvent> expectedColorEventBlue =
                Optional.of(new ColorEvent("BLUE"));

        Optional<ColorEvent> actualColorEventRed =
                colorMapper.getColorEvent(new ColorRequest(true, "255,0,0"));
        Optional<ColorEvent> actualColorEventGreen =
                colorMapper.getColorEvent(new ColorRequest(true, "0,255,0"));
        Optional<ColorEvent> actualColorEventBlue =
                colorMapper.getColorEvent(new ColorRequest(true, "0,0,255"));

        assertEquals(expectedColorEventRed, actualColorEventRed);
        assertEquals(expectedColorEventGreen, actualColorEventGreen);
        assertEquals(expectedColorEventBlue, actualColorEventBlue);
    }

    @Test
    void getColorEvent_ValidColorRequest_EmptyOptional() {

        Optional<ColorEvent> expectedColorEventEmpty = Optional.empty();

        Optional<ColorEvent> actualColorEventRed =
                colorMapper.getColorEvent(new ColorRequest(true, "falseValue"));

        assertEquals(expectedColorEventEmpty, actualColorEventRed);
    }


}