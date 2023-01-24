package com.flow.project.repository;

import com.flow.project.entity.UseFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface FileRepository extends JpaRepository<UseFile, Long> {
    @Transactional(readOnly=true)
    Optional<UseFile> findTopByIpOrderByNoDesc(String ip);
}
