package com.example.springbootdemo.controller;

import com.example.springbootdemo.dto.CreateTaskRequest;
import com.example.springbootdemo.exception.TaskNotFoundException;
import com.example.springbootdemo.model.Task;
import com.example.springbootdemo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 任务相关接口，共三个：
 * 1. POST   /api/tasks       创建任务
 * 2. GET    /api/tasks       查询任务列表
 * 3. GET    /api/tasks/{id}  按 ID 查询任务详情
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /** 接口一：创建任务 */
    @PostMapping
    public ResponseEntity<Task> create(@Valid @RequestBody CreateTaskRequest request) {
        Task task = taskService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    /** 接口二：查询全部任务 */
    @GetMapping
    public List<Task> list() {
        return taskService.findAll();
    }

    /** 接口三：按 ID 查询任务 */
    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id) {
        return taskService.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }
}
