package com.example.server.dto.param;

import com.example.server.util.ValidateUtil;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class USidParam {
    @Pattern(regexp = ValidateUtil.UID)
    private String uid;
    @Pattern(regexp = ValidateUtil.UID)
    private String sid;
}
