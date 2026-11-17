package com.example.springbootdemo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 创建任务的请求体。
 */
public record CreateTaskRequest(
        @NotBlank(message = "title 不能为空")
        @Size(max = 100, message = "title 长度不能超过 100 个字符")
        String title,

        @Size(max = 500, message = "description 长度不能超过 500 个字符")
        String description
) {
}
