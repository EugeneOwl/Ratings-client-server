package com.example.server.service;

import com.example.server.dto.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {

    /**
     * Returns task Dto of task with the given identifier.
     *
     * @param id must not be {@literal null}.
     * @return task Dto of task with the given identifier.
     * @throws javax.persistence.EntityNotFoundException if no task exists for given id.
     */
    TaskDto getTaskById(Long id);

    /**
     * Saves new task received from given task Dto.
     *
     * @param taskDto
     * @throws javax.persistence.EntityNotFoundException if no user exists for task Dto user id
     *                                                   or any of task parent user id.
     */
    void addTask(TaskDto taskDto);

    /**
     * Updates existing task received from given task Dto.
     *
     * @param taskDto must have existing task identifier.
     * @throws javax.persistence.EntityNotFoundException if no task exists for given task Dto
     * or no user exists for task Dto user id.
     */
    void updateTask(TaskDto taskDto);

    /**
     * Returns task Dto list of all tasks from database.
     *
     * @return task Dto list of all task from database.
     */
    List<TaskDto> getAllTasks();

    /**
     * Removes existing task from database by given identifier.
     *
     * @param id must not be {@literal null}.
     * @throws org.springframework.dao.EmptyResultDataAccessException if no task exists for given id.
     */
    void removeTask(Long id);

    /**
     * Returns task Dto page by given page number, page size, sort field and filter pattern.
     *
     * @param pageable      must contain positive page number, positive page size and existing sort field.
     * @param filterPattern is appropriate if it takes place at the beginning, middle or end of task label
     *                      of equals to task identifier.
     * @return task Dto page by given page number, page size, sort field and filter pattern.
     * @throws org.hibernate.exception.SQLGrammarException when sorting field does not exist.
     * @throws IllegalArgumentException when page number is negative.
     */
    Page<TaskDto> getPageOfTasks(Pageable pageable,
                                 String filterPattern);
}
