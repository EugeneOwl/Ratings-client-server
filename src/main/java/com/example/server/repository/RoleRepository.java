package com.example.server.repository;

import com.example.server.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM roles WHERE id = ?1 OR LOWER(label) LIKE %?2%")
    Page<Role> findByIdOrLabel(Long Id, String label, Pageable pageRequest);
}
