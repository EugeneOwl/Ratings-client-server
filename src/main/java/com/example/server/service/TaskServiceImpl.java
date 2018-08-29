package com.example.server.service;

import com.example.server.dto.TaskDto;
import com.example.server.model.Task;
import com.example.server.repository.TaskRepository;
import com.example.server.transformer.TaskTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskTransformer taskTransformer;

    @Autowired
    private StringParser stringParser;

    @Override
    public TaskDto getTaskById(final Long id) {
        final Task task = Optional.of(taskRepository.getOne(id))
                .orElseThrow(EntityExistsException::new);
        log.info("Task was taken by id: {}", task);

        return taskTransformer.transform(task);
    }

    @Override
    public void addTask(final TaskDto taskDto) {
        final Task task = taskTransformer.transform(taskDto);
        taskRepository.save(task);
        log.info("Task was added: {}", task);
    }

    @Override
    public void updateTask(final TaskDto taskDto) {
        final Task task = Optional.of(taskRepository.getOne(taskDto.getId()))
                .orElseThrow(EntityNotFoundException::new);
        task.update(taskTransformer.transform(taskDto));

        taskRepository.save(task);
        log.info("Task was updated: {}", task);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        final List<Task> tasks = taskRepository.findAll();

        return tasks.stream()
                .peek(t -> log.info("Task was taken: {}", t))
                .map(taskTransformer::transform)
                .collect(Collectors.toList());
    }

    @Override
    public void removeTask(final Long id) {
        taskRepository.deleteById(id);
        log.info("Task with id = {} was removed.", id);
    }

    @Override
    public Page<TaskDto> getPageOfTasks(final Pageable pageable,
                                        final String filterPattern) {

        return taskRepository.findByIdOrLabel(
                stringParser.getLongFromPattern(filterPattern),
                filterPattern.toLowerCase(),
                pageable
        ).map(taskTransformer::transform);
    }
}
