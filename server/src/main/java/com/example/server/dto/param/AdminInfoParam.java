package com.example.server.dto.param;

import com.example.server.annotation.StringValue;
import com.example.server.util.ValidateUtil;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Data
public class AdminInfoParam {
    @StringValue(regex = ValidateUtil.NICKNAME, min = 1, max = 20)
    private String name;
    @StringValue(regex = ValidateUtil.USERNAME, min = 3, max = 10)
    private String username;
    @Pattern(regexp = ValidateUtil.USERNAME, message = "密码含非法字符")
    @Length(min = 6, max = 16, message = "密码长度为6-16位")
    private String password;
}
