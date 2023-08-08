package com.example.server.dto.param;

import com.example.server.constant.GenderEnum;
import com.example.server.constant.ServiceEnum;
import com.example.server.util.ValidateUtil;
import com.example.server.annotation.EnumValue;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
public class RequireParam {
    @Pattern(regexp = ValidateUtil.UID, message = "非法ID")
    private String uid;
    @Pattern(regexp = ValidateUtil.UID, message = "非法ID")
    private String sid;
    @EnumValue(enumClass = ServiceEnum.class)
    private String serveType;
    @EnumValue(enumClass = GenderEnum.class)
    private String gender;
    @NotEmpty
    private List<String> voice;
    @NotEmpty
    private List<String> nature;
    // TODO: 2023/8/7 拓展校验
    private String detail;
    // TODO: 2023/8/7 时间校验
    private Instant estimatedStart;
    private Integer estimatedDuration;
    // TODO: 2023/8/7 价格校验
    private BigDecimal priceLow;
    private BigDecimal priceHigh;
    private String note;
}
