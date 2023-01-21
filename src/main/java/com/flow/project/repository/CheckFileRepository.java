package com.flow.project.repository;

import com.flow.project.entity.CheckFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CheckFileRepository extends JpaRepository<CheckFile, Long> {
    @Transactional(readOnly=true)
    @Query("select c from CheckFile c order by c.no desc")
    Optional<CheckFile> findCheck();
}
