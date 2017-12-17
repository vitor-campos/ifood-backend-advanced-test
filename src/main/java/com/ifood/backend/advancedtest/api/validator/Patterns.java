package com.ifood.backend.advancedtest.api.validator;

public class Patterns {

    public static final String COORDINATE_PATTERN_REGEX = "([+-]?\\d+\\.?\\d+)\\s*,\\s*([+-]?\\d+\\.?\\d+)";
    public static final String CITY_NAME_PATTERN_REGEX = "[^0-9]";

    private Patterns() {};
}
