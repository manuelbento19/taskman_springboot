package com.bentoo.taskman.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "tb_tasks")
public class Task {
    @Id
    @GeneratedValue(generator = "UUID")
    public UUID id;

    @Column(length = 80)
    public String title;
    public String description;
    public LocalDateTime startedAt;
    public LocalDateTime endAt;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    public User user;

    @CreationTimestamp
    public LocalDateTime createdAt;


}
