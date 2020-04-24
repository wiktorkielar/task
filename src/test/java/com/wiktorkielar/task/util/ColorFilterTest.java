package com.wiktorkielar.task.util;

import com.wiktorkielar.task.model.ColorRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColorFilterTest {

    private ColorFilter colorFilter;

    @BeforeEach
    void setup() {
        colorFilter = new ColorFilter();
    }

    @Test
    void getRequests_NullColorField_EmptyList() {

        List<ColorRequest> colorRequests = List.of(new ColorRequest(true, null));

        List<ColorRequest> requests = colorFilter.getRequests(colorRequests);

        assertTrue(requests.isEmpty());
    }

    @Test
    void getRequests_EmptyColorField_EmptyList() {

        List<ColorRequest> colorRequests = List.of(new ColorRequest(true, ""));
        List<ColorRequest> requests = colorFilter.getRequests(colorRequests);

        assertTrue(requests.isEmpty());
    }

    @Test
    void getRequests_ColorFieldWithWhitespaces_EmptyList() {

        List<ColorRequest> colorRequests = List.of(new ColorRequest(true, "   "));
        List<ColorRequest> requests = colorFilter.getRequests(colorRequests);

        assertTrue(requests.isEmpty());
    }

    @Test
    void getRequests_PublishFieldIsFalse_EmptyList() {

        List<ColorRequest> colorRequests = List.of(
                new ColorRequest(false, "255,0,0"),
                new ColorRequest(false, "0,255,0"),
                new ColorRequest(false, "0,0,255")
        );

        List<ColorRequest> requests = colorFilter.getRequests(colorRequests);

        assertTrue(requests.isEmpty());
    }

    @Test
    void getRequests_PublishFieldIsTrue_NonEmptyList() {

        List<ColorRequest> colorRequests = List.of(
                new ColorRequest(true, "255,0,0"),
                new ColorRequest(true, "0,255,0"),
                new ColorRequest(true, "0,0,255")
        );

        List<ColorRequest> requests = colorFilter.getRequests(colorRequests);

        assertFalse(requests.isEmpty());
        assertEquals(requests.size(), colorRequests.size());
    }
}