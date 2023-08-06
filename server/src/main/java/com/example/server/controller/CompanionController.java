package com.example.server.controller;

import com.example.server.dto.param.USidParam;
import com.example.server.dto.param.StatusParam;
import com.example.server.entity.Companion;
import com.example.server.service.impl.CompanionServiceImpl;
import com.example.server.util.ApiResponse;
import com.example.server.util.PageQuery;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/companion")
@RequiredArgsConstructor
@Validated
public class CompanionController {
    private final CompanionServiceImpl companionService;

    @GetMapping("/page")
    public ApiResponse<PageQuery<Companion>> getCompanionByPage(@Length(max = 20, message = "长度超限") String query,
                                                                @RequestParam(defaultValue = "1") @Min(1) Integer pageNum,
                                                                @RequestParam(defaultValue = "10") @Min(1) Integer pageSize){
        return ApiResponse.success(companionService.getCompanionList(query, pageNum, pageSize));
    }

    @PostMapping
    public ApiResponse<Integer> addCompanion(@RequestBody @Valid USidParam param) {
        return ApiResponse.success(companionService.addCompanion(param));
    }

    @PutMapping
    public ApiResponse<Integer> updateCompanion(@RequestBody @Valid StatusParam param) {
        return ApiResponse.success(companionService.updateStatus(Integer.parseInt(param.getId()), param.getStatus()));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Integer> deleteCompanion(@PathVariable("id") @Min(0) Integer id) {
        return ApiResponse.success(companionService.deleteCompanion(id));
    }
}
