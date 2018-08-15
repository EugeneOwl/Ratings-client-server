package com.example.server.service;

import com.example.server.dto.TaskDto;
import com.example.server.model.Role;
import com.example.server.model.Task;
import com.example.server.repository.TaskRepository;
import com.example.server.transformer.TaskTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskTransformer taskTransformer;

    @Override
    public TaskDto getTaskById(final Long id) {
        if (taskRepository.existsById(id)) {
            final Task task = taskRepository.getOne(id);
            log.info("Task was taken by id: {}", task);

            return taskTransformer.transform(task);
        }
        log.info("Attempt to take not existing task with id = {}" + id);

        return null;
    }

    @Override
    public void addTask(final TaskDto taskDto) {
        final Task task = taskTransformer.transform(taskDto);
        taskRepository.save(task);
        log.info("Task was added: {}", task);
    }

    @Override
    public void updateTask(final TaskDto taskDto) {
        final Task task = taskTransformer.transform(taskDto);
        taskRepository.save(task);
        log.info("Task was updated: {}", task);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        final List<Task> tasks = taskRepository.findAll();
        for (final Task task : tasks) {
            log.info("Task was taken: {}", task);
        }

        return tasks.stream().map(taskTransformer::transform)
                .collect(Collectors.toList());
    }

    @Override
    public void removeTask(final Long id) {
        taskRepository.deleteById(id);
        log.info("Task with id = {} was removed.", id);
    }

    @Override
    public List<Task> getTaskListByIds(final List<Long> ids) {
        return ids.stream().map(this::getTaskById)
                .filter(Objects::nonNull).map(taskTransformer::transform)
                .sorted(comparing(Task::getId))
                .collect(Collectors.toList());
    }
}
