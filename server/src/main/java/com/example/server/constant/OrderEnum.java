package com.example.server.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderEnum {
    CREATE("CREATE"),
    PEND("PEND"),
    DISPATCHED("DISPATCHED"),
    CONFIRMED("CONFIRMED"),
    PROGRESS("PROGRESS"),
    COMPLETED("COMPLETED"),
    REVIEW("REVIEW"),
    CANCELLED("CANCELLED"),
    REFUNDED("REFUNDED"),
    FAILED("FAILED"),
    HOLD("HOLD"),
    ARCHIVED("ARCHIVED");

    private final String code;
}
