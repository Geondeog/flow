package com.flow.project.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@ToString @Entity
@Table(name="use_file")
@EqualsAndHashCode(of="no")
public class UseFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int no;

    @Lob
    private String file;
    private String ip;

    @CreationTimestamp
    private LocalDateTime updatedate = LocalDateTime.now();
}
