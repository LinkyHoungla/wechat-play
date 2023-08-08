package com.example.server.dto.param;

import com.example.server.constant.OrderEnum;
import com.example.server.constant.StatusEnum;
import com.example.server.util.ValidateUtil;
import com.example.server.annotation.EnumValue;
import com.example.server.util.validator.ValidGroup;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class StatusParam {
    @Pattern(groups = ValidGroup.Type.Account.class, regexp = ValidateUtil.UID, message = "非法ID")
    @Pattern(groups = ValidGroup.Type.Goods.class, regexp = ValidateUtil.ID, message = "非法ID")
    private String id;
    @EnumValue(enumClass = StatusEnum.class, groups = { ValidGroup.Type.Account.class, ValidGroup.Type.Goods.class })
    @EnumValue(enumClass = OrderEnum.class, groups = ValidGroup.Type.Order.class)
    private String status;
}
