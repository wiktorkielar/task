package com.wiktorkielar.task;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class ApplicationTest {

    @BeforeAll
    public static void setUp() {
        configureHost();
        configurePort();
        configureBasePath();
        startServer();
    }

    private static void configureHost() {
        String baseHost = System.getProperty("server.host");
        if (baseHost == null) {
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;
    }

    private static void configurePort() {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = Integer.parseInt("8000");
        } else {
            RestAssured.port = Integer.parseInt(port);
        }
    }

    private static void configureBasePath() {
        String basePath = System.getProperty("server.base");
        if (basePath == null) {
            basePath = "/";
        }
        RestAssured.basePath = basePath;
    }

    private static void startServer() {
        Application.main(new String[]{});
    }

    @Test
    public void main_JSONPayload_ResponseAndStatusOK() {
        String payload = "[\n" +
                "  {\n" +
                "    \"publish\": true,\n" +
                "    \"color\": \"255,0,0\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"publish\": true,\n" +
                "    \"color\": \"0,255,0\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"publish\": true,\n" +
                "    \"color\": \"0,0,255\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"publish\": false,\n" +
                "    \"color\": \"\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"publish\": true,\n" +
                "    \"color\": \"\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"publish\": true,\n" +
                "    \"color\": null\n" +
                "  }\n" +
                "]";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .post("/publish")
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

}