package org.example;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private final Map<String, String> queryParams = new HashMap<>();

    public Request(String query) {
        if (query != null && !query.isEmpty()) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                queryParams.put(keyValue[0], keyValue[1]);
            }
        }
    }

    public String getValues(String key) {
        return queryParams.get(key);
    }
}