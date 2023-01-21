package com.flow.project.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@ToString @Entity
@Table(name="check_file")
@EqualsAndHashCode(of="no")
public class CheckFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int no;

    @Lob
    private String file;

    @CreationTimestamp
    private LocalDateTime updatedate = LocalDateTime.now();
}
