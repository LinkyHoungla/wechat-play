package com.example.server.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServiceEnum {
    SERVICE("SERVICE"),
    VOICE("VOICE"),
    NATURE("NATURE");

    private final String code;
}
