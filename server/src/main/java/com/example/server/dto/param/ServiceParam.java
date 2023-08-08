package com.example.server.dto.param;

import com.example.server.constant.GenderEnum;
import com.example.server.constant.ServiceEnum;
import com.example.server.annotation.EnumValue;
import com.example.server.util.validator.ValidGroup;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class ServiceParam {
    @Min(groups = ValidGroup.Crud.Update.class, value = 0, message = "ID非法")
    private Integer id;
    @EnumValue(groups = ValidGroup.Crud.Create.class, enumClass = ServiceEnum.class)
    private String type;
    @NotBlank(message = "名称不能为空")
    private String name;
    @EnumValue(enumClass = GenderEnum.class)
    private String gender;
}
