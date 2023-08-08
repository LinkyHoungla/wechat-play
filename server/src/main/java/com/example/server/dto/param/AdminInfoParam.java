package com.example.server.dto.param;

import com.example.server.annotation.StringValue;
import com.example.server.util.ValidateUtil;
import lombok.Data;

@Data
public class AdminInfoParam {
    @StringValue(regex = ValidateUtil.NICKNAME, min = 1, max = 20)
    private String name;
    @StringValue(regex = ValidateUtil.USERNAME, min = 3, max = 10)
    private String username;
    @StringValue(regex = ValidateUtil.USERNAME, min = 6, max = 16)
    private String password;
}
