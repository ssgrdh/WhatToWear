package com.dinesh.whattowear.model;

// Nested record for Weather (weather conditions)
public record Weather(
        int id,
        String main,
        String description
) {
}
