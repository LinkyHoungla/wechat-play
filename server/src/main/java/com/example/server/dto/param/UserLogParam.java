package com.example.server.dto.param;

import com.example.server.util.ValidateUtil;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserLogParam {
    @NotBlank
    @Length(min = 1, max = 50)
    private String wxid;
    @Pattern(regexp = ValidateUtil.PHONE)
    private String phone;
}
