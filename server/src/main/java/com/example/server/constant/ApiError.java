package com.example.server.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiError {
    E401(401, "Unauthorized"),
    E403(403, "Forbidden"),
    E450(450, "Required Login"),
    E451(451, "Invalid Login"),
    E452(452, "Expired Token"),
    E453(453, "Illegal Token"),
    E460(460, "Invalid Database Condition"),
    E404(404, "Other");

    private final Integer code;
    private final String message;
}
