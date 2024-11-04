package com.bentoo.taskman.models;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name="tb_users")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    public UUID id;

    public String name;

    @Column(unique = true)
    public String email;
    public String password;

    @CreationTimestamp
    public LocalDateTime createdAt;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;
}
