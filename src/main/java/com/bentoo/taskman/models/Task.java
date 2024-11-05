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
    public UUID id;

    @Column(length = 80)
    public String title;
    public String description;
    public LocalDateTime startedAt;
    public LocalDateTime endAt;

    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    @JsonIgnore()
    public User user;

    @CreationTimestamp
    public LocalDateTime createdAt;


}
