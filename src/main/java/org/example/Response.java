package org.example;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private int statusCode;
    private String body;
    private Map<String, String> headers;

    public Response() {
        this.statusCode = 200; // Default to 200 OK
        this.body = "";
        this.headers = new HashMap<>();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeader(String key, String value) {
        this.headers.put(key, value);
    }

    public String getHeader(String key) {
        return this.headers.get(key);
    }
}