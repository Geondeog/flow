package com.flow.project.repository;

import com.flow.project.entity.SaveFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SaveFileRepository extends JpaRepository<SaveFile, Long> {

    @Transactional(readOnly=true)
    List<SaveFile> findAllByIp(String ip);

    @Transactional
    void deleteByUno(int uno);
}
