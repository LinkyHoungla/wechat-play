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
    E454(454, "Account Banned"),
    E455(455, "Account Deleted"),
    E456(456, "Account Error"),
    E460(460, "Invalid Database Condition"),
    E461(461, "Duplicate Unique ID"),
    E462(462, "File OriginalName Null"),
    E463(463, "File Delete Error"),
    E464(464, "File Save Error"),
    E465(465, "File Save Error"),
    E404(404, "Other");

    private final Integer code;
    private final String message;
}
