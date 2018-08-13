package com.example.server.controller;

import com.example.server.dto.TaskDto;
import com.example.server.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "server/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<TaskDto> getAllTasks() {

        return taskService.getAllTasks();
    }

    @GetMapping("{id}")
    public TaskDto getTaskById(@PathVariable("id") final Long id) {

        return taskService.getTaskById(id);
    }

    @PutMapping
    public TaskDto addTask(@RequestBody final TaskDto taskDto) {
        taskService.addTask(taskDto);

        return taskDto;
    }

    @PostMapping
    public TaskDto updateTask(@RequestBody final TaskDto taskDto) {
        taskService.updateTask(taskDto);

        return taskDto;
    }

    @DeleteMapping("{id}")
    public Long removeTask(@PathVariable("id") final Long id) {
        taskService.removeTask(id);

        return id;
    }
}
