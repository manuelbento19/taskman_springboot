package com.bentoo.taskman.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name="tb_users")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    public UUID Id;

    private String name;
    private String email;
    private String password;

    @CreationTimestamp
    public LocalDateTime createdAt;
}
