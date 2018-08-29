package com.example.server.controller;

import com.example.server.dto.TaskDto;
import com.example.server.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
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

    @GetMapping("/page")
    public Page<TaskDto> getPageOfTasks(
            final Pageable pageable,
            @RequestParam(value = "filterPattern", defaultValue = "") final String filterPattern
    ) {

        return taskService.getPageOfTasks(pageable, filterPattern);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public TaskDto addTask(@Valid @RequestBody final TaskDto taskDto) {
        taskService.addTask(taskDto);

        return taskDto;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public TaskDto updateTask(@Valid @RequestBody final TaskDto taskDto) {
        taskService.updateTask(taskDto);

        return taskDto;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public Long removeTask(@PathVariable("id") final Long id) {
        taskService.removeTask(id);

        return id;
    }
}
