package com.example.server.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiError {
    E401(401, "Unauthorized"),
    E403(403, "Forbidden"),
    E440(440, "Parameter Missing"),
    E441(441, "Wrong Format"),
    E442(442, "Range Error"),
    E450(450, "Login Required"),
    E451(451, "Invalid Login"),
    E452(452, "Expired Token"),
    E453(453, "Illegal Token"),
    E460(460, "Invalid Database Condition"),
    E461(461, "Duplicate Unique ID"),
    E404(404, "Other");

    private final Integer code;
    private final String message;
}
