package com.example.server.dto.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RoleParam {
    @NotBlank(message = "名称为必填项")
    private String name;
    private String desc;
}
