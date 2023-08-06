package com.example.server.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GameEnum {
    GAME("GAME"),
    SERVER("SERVER"),
    MODE("MODE"),
    RANK("RANK");

    private final String code;
}
