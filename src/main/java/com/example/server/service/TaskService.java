package com.example.server.service;

import com.example.server.dto.TaskDto;
import com.example.server.model.Task;

import java.util.List;

public interface TaskService {

    TaskDto getTaskById(Long id);

    void addTask(TaskDto taskDto);

    void updateTask(TaskDto taskDto);

    List<TaskDto> getAllTasks();

    void removeTask(Long id);

    List<Task> getTaskListByIds(List<Long> ids);
}
