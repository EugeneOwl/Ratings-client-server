package com.example.server.service;

import com.example.server.dto.TaskDto;

import java.util.List;

public interface TaskService {

    TaskDto getTaskById(Long id);

    void addTask(TaskDto taskDto);

    void updateTask(TaskDto taskDto);

    List<TaskDto> getAllTasks();

    void removeTask(Long id);
}
