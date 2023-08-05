package com.example.server.dto.param;

import com.example.server.constant.GenderEnum;
import com.example.server.util.ValidateUtil;
import com.example.server.util.validator.EnumValue;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.Instant;

@Data
public class UserInfoParam {
    @NotBlank(message = "ID不能为空")
    @Pattern(regexp = ValidateUtil.UID, message = "ID非法")
    private String id;
    @NotBlank(message = "昵称不能为空")
    @Length(min = 3, max = 10, message = "昵称长度3-10")
    private String nickname;
    @EnumValue(enumClass = GenderEnum.class, message = "性别非法")
    private String gender;
    @NotNull(message = "地址不能为NULL")
    @Length(max = 10, message = "地址长度最大10")
    private String location;
    @NotNull(message = "生日不能为NULL")
    private Instant birth;
    @Min(value = 18, message = "年龄非法")
    private Integer age;
}
