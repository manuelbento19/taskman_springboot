package com.bentoo.taskman.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "tb_tasks")
@Data
public class Task {
    @Id
    @GeneratedValue(generator = "UUID")
    public UUID id;

    @Column(length = 80)
    public String title;
    public String description;
    public LocalDateTime startedAt;
    public LocalDateTime endAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    public User user;

    @CreationTimestamp
    public LocalDateTime createdAt;


}
