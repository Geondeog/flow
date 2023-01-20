package com.flow.project.repository;

import com.flow.project.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {
    Optional<File> findTopByIpOrderByUpdatedateDesc(String ip);
}
