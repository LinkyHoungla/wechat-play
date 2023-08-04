package com.example.server.dto.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class LoginParam {
    @NotBlank(message = "用户名不为空")
    @Length(min = 3, max = 20, message = "用户名长度为3-10")
    private String username;
    @NotBlank(message = "密码不为空")
    @Length(min = 6, max = 16, message = "密码长度为6-16")
    private String password;
}
