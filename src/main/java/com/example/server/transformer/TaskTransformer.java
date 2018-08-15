package com.example.server.transformer;

import com.example.server.dto.TaskDto;
import com.example.server.model.Task;
import com.example.server.repository.TaskRepository;
import com.example.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.Optional;

@Component
public class TaskTransformer implements Transformer<Task, TaskDto> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task transform(final TaskDto taskDto) {
        final Task task = Task.builder()
                .label(taskDto.getLabel())
                .description(taskDto.getDescription())
                .evaluation(taskDto.getEvaluation())
                .user(Optional.of(userRepository.getOne(taskDto.getUser().getId()))
                .orElseThrow(EntityNotFoundException::new))
                .build();
        task.setId(taskDto.getId());
        if (Objects.nonNull(taskDto.getParent())) {
            task.setParent(Optional.of(taskRepository.getOne(taskDto.getParent().getId()))
                    .orElseThrow(EntityNotFoundException::new));
        }

        return task;
    }

    @Override
    public TaskDto transform(final Task task) {
        if (Objects.isNull(task)) {

            return null;
        }

        return TaskDto.builder()
                .id(task.getId())
                .label(task.getLabel())
                .description(task.getDescription())
                .evaluation(task.getEvaluation())
                .parent(transform(task.getParent()))
                .build();
    }
}
