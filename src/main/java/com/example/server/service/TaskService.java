package com.example.server.service;

import com.example.server.dto.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {

    TaskDto getTaskById(Long id);

    void addTask(TaskDto taskDto);

    void updateTask(TaskDto taskDto);

    List<TaskDto> getAllTasks();

    void removeTask(Long id);

    Page<TaskDto> getPageOfTasks(Pageable pageable,
                                 String filterPattern);
}
