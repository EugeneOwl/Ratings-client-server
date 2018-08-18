package com.example.server.repository;

import com.example.server.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query(nativeQuery = true,
            value = "SELECT * FROM users WHERE id = ?1 OR LOWER(username) LIKE %?2%")
    Page<User> findByIdOrUsername(Long Id, String username, Pageable pageRequest);
}
