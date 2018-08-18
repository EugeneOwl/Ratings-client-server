package com.example.server.service;

import com.example.server.dto.TaskDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {
    int TASK_COUNT_PER_PAGE = 3;

    TaskDto getTaskById(Long id);

    void addTask(TaskDto taskDto);

    void updateTask(TaskDto taskDto);

    List<TaskDto> getAllTasks();

    void removeTask(Long id);

    Page<TaskDto> getPageOfTasks(int page,
                                 String sortByColumn,
                                 String filterPattern);
}
