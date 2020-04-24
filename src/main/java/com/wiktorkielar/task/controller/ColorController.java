package com.wiktorkielar.task.controller;

import com.wiktorkielar.task.model.ColorRequest;
import com.wiktorkielar.task.model.Response;
import com.wiktorkielar.task.service.ColorService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

import javax.inject.Inject;
import java.util.List;

@Controller
public class ColorController {

    @Inject
    private ColorService colorService;

    @Post("/publish")
    public HttpResponse<Response> publishColors(@Body List<ColorRequest> colorRequests) {
        colorService.publish(colorRequests);
        return HttpResponse.ok(new Response(true));
    }
}
