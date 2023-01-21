package com.flow.project.repository;

import com.flow.project.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {
    @Transactional(readOnly=true)
    Optional<File> findTopByIpOrderByNoDesc(String ip);
}
