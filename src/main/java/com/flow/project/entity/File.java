package com.flow.project.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@ToString @Entity
@Table(name="use_file")
@EqualsAndHashCode(of="no")
public class File {

    @Id
    private String no;
    private String fileString;

    @CreationTimestamp
    private Timestamp updatedate;
}
