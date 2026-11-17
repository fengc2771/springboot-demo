package com.example.springbootdemo.service;

import com.example.springbootdemo.dto.CreateTaskRequest;
import com.example.springbootdemo.model.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 任务服务，使用内存 Map 存储，进程重启后数据清空。
 */
@Service
public class TaskService {

    private final Map<Long, Task> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public Task create(CreateTaskRequest request) {
        long id = idGenerator.incrementAndGet();
        Task task = new Task(id, request.title().trim(), request.description(), false, LocalDateTime.now());
        store.put(id, task);
        return task;
    }

    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>(store.values());
        tasks.sort(Comparator.comparing(Task::getId));
        return tasks;
    }

    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}
