package com.example.springbootdemo.exception;

/**
 * 任务不存在时抛出，由全局异常处理器转换为 404。
 */
public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(Long id) {
        super("任务不存在: id=" + id);
    }
}
