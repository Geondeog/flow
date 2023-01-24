package com.flow.project.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@ToString @Entity
@Table(name="save_file")
@EqualsAndHashCode(of="no")
public class SaveFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int uno;

    @Lob
    @Column(name = "originalFileName")
    private String name;
    @Lob
    private String savedFileName;
    @Lob
    private String savedFilePath;

    private String ip;

    @ManyToOne
    @JoinColumn(name = "no")
    private UseFile file;

    @CreationTimestamp
    private LocalDateTime updatedate = LocalDateTime.now();
}
