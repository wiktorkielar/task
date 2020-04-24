package com.wiktorkielar.task.service;

import com.wiktorkielar.task.model.ColorRequest;

import java.util.List;

public interface ColorService {
    void publish(List<ColorRequest> colorRequests);
}
