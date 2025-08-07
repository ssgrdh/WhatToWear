package com.dinesh.whattowear.model;

// Nested record for Main (temperature information)
public record Main(
        double temp,
        int humidity,
        double feels_like
) {}
