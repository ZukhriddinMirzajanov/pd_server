package com.example.PD_server.repository;

import com.example.PD_server.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query("SELECT r FROM Request r WHERE r.user.username = :username")
    List<Request> findByUserUsername(@Param("username") String username);
}
