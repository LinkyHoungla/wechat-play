package com.example.server.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatusEnum {
    ACTIVE("ACTIVE"),
    BANNED("BANNED"),
    DELETED("DELETED");

    private final String code;
}
