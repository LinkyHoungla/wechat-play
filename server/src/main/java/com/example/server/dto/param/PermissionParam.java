package com.example.server.dto.param;

import com.example.server.util.validator.ValidGroup;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PermissionParam {
    @NotNull(groups = {ValidGroup.Crud.Update.class, ValidGroup.Crud.Delete.class}, message = "ID不能为空")
    @Min(groups = {ValidGroup.Crud.Update.class, ValidGroup.Crud.Delete.class}, value = 0, message = "ID非法")
    private Integer id;
    @NotNull(groups = {ValidGroup.Crud.Create.class}, message = "PID不能为空")
    @Min(groups = {ValidGroup.Crud.Create.class}, value = -1, message = "PID非法")
    private Integer pid;
    @NotBlank(message = "名称不能为空")
    private String name;
    @NotBlank(message = "路径不能为空")
    private String path;
    private String icon;
}
