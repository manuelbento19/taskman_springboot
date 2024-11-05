package com.bentoo.taskman.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "tb_tasks")
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(length = 80)
    private String title;
    private String description;
    private LocalDateTime startedAt;
    private LocalDateTime endAt;

    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    @JsonIgnore()
    private User user;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
