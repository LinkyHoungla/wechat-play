package com.example.server.dto.param;

import com.example.server.util.ValidateUtil;
import com.example.server.annotation.MoneyValue;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
public class BalanceParam {
    @Pattern(regexp = ValidateUtil.UID)
    private Integer id;
    @Pattern(regexp = ValidateUtil.UID)
    private String uid;
    @Pattern(regexp = ValidateUtil.UID)
    private String sid;
    @MoneyValue
    private BigDecimal balance;
    @NotBlank(message = "变动原因不能为空")
    private String content;
}
