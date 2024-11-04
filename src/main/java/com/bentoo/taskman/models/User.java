package com.bentoo.taskman.models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name="tb_users")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    public UUID id;

    @Column
    public String name;

    @Column
    public String email;
    public String password;

    @CreationTimestamp
    public LocalDateTime createdAt;
}
