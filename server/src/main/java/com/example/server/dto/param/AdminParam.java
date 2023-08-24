package com.example.server.dto.param;

import com.example.server.constant.StatusEnum;
import com.example.server.util.ValidateUtil;
import com.example.server.annotation.EnumValue;
import com.example.server.util.validator.ValidGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class AdminParam {
    @Pattern(groups = ValidGroup.Crud.Update.class, regexp = ValidateUtil.ID, message = "非法ID")
    private String id;
    @NotBlank(groups = ValidGroup.Crud.Create.class, message = "昵称不能为空")
    @Length(groups = ValidGroup.Crud.Create.class, min = 1,max = 10,message = "昵称长度1-10")
    private String name;
    @EnumValue(groups = ValidGroup.Crud.Update.class, enumClass = StatusEnum.class, message = "非法参数")
    private String status;
    @Min(1)
    private Integer rid;
}
