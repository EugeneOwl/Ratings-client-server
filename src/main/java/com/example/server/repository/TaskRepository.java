package com.example.server.repository;

import com.example.server.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM tasks WHERE id = ?1 OR LOWER(label) LIKE %?2%")
    Page<Task> findByIdOrLabel(Long Id, String label, Pageable pageRequest);
}
