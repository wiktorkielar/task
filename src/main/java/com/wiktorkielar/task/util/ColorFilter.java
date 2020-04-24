package com.wiktorkielar.task.util;

import com.wiktorkielar.task.model.ColorRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Singleton
public class ColorFilter {

    private static final Logger logger = LoggerFactory.getLogger(ColorFilter.class);

    public List<ColorRequest> getRequests(List<ColorRequest> colorRequests) {

        return getPublishableRequests(getValidatedRequests(colorRequests));
    }

    private List<ColorRequest> getPublishableRequests(List<ColorRequest> colorRequests) {

        return colorRequests.stream()
                .filter(ColorRequest::isPublish)
                .collect(Collectors.toList());
    }

    private List<ColorRequest> getValidatedRequests(List<ColorRequest> colorRequests) {

        return colorRequests.stream()
                .filter(isNotNull)
                .filter(isNotEmpty)
                .filter(hasNoWhitespaces)
                .collect(Collectors.toList());
    }

    private final Predicate<ColorRequest> isNotNull = colorRequest -> {

        if (colorRequest.getColor() == null) {
            logger.warn("Field 'color' cannot be null.");
            return false;
        }
        return true;
    };

    private final Predicate<ColorRequest> isNotEmpty = colorRequest -> {

        if (colorRequest.getColor().isEmpty()) {
            logger.warn("Field 'color' cannot be empty.");
            return false;
        }
        return true;
    };

    private final Predicate<ColorRequest> hasNoWhitespaces = colorRequest -> {

        if (colorRequest.getColor().trim().isEmpty()) {
            logger.warn("Field 'color' cannot contain whitespace characters only.");
            return false;
        }
        return true;
    };
}
