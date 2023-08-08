package com.example.server.dto.param;

import com.example.server.util.ValidateUtil;
import com.example.server.util.validator.ValidGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class StoreParam {
    @Pattern(regexp = ValidateUtil.UID, message = "非法ID", groups = ValidGroup.Crud.Update.class)
    private String id;
    @NotBlank(message = "名称不能为空")
    @Length(min = 2, max = 15, message = "名称长度2-15")
    private String name;
    private String desc;
    @Pattern(regexp = ValidateUtil.UID, message = "非法ID", groups = ValidGroup.Crud.Create.class)
    private String oid;
    @Pattern(regexp = ValidateUtil.CHINESE, message = "非中文姓名", groups = ValidGroup.Crud.Create.class)
    private String owner;
    @Pattern(regexp = ValidateUtil.PHONE, message = "无效手机号", groups = ValidGroup.Crud.Create.class)
    private String phone;
}
