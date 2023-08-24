package com.example.server.dto.param;

import com.example.server.util.validator.ValidGroup;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RoleParam {
    @Min(value = 1, groups = ValidGroup.Crud.Update.class)
    private Integer id;
    @NotBlank(message = "名称为必填项")
    private String name;
    private String desc;
}
