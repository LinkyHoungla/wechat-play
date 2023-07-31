package com.example.server.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiError {
    E401(401, "Unauthorized"),
    E403(403, "Forbidden"),
    E451(451, "Login Required"),
    E452(452, "Token Expired"),
    E453(453, "Illegal Token"),
    E404(404, "Other");

    private final Integer code;
    private final String message;
}
