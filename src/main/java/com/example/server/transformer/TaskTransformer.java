package com.example.server.transformer;

import com.example.server.dto.TaskDto;
import com.example.server.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;

@Component
public class TaskTransformer implements Transformer<Task, TaskDto> {

    @Autowired
    private UserTransformer userTransformer;

    @Override
    public Task transform(final TaskDto taskDto) {
        if (Objects.isNull(taskDto)) {

            return null;
        }
        final Task task = Task.builder()
                .label(taskDto.getLabel())
                .description(taskDto.getDescription())
                .evaluation(taskDto.getEvaluation())
                .parent(transform(taskDto.getParent()))
                .user(userTransformer.transform(taskDto.getUser()))
                .build();
        task.setId(taskDto.getId());
        task.setSubTasks(new ArrayList<>());

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
