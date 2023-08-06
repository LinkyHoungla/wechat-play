package com.example.server.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GenderEnum {
    MALE("MALE"),
    FEMALE("FEMALE"),
    ALL("ALL");

    private final String gender;
}
