package com.example.server.dto.param;

import com.example.server.constant.StatusEnum;
import com.example.server.util.validator.EnumValue;
import com.example.server.util.validator.ValidGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class AdminParam {
    @NotBlank(message = "昵称不能为空")
    @Length(min = 1,max = 10,message = "昵称长度1-10")
    private String name;
    @EnumValue(enumClass = StatusEnum.class, message = "非法参数")
    private String status;
    @Min(0)
    private Integer rid;
}
