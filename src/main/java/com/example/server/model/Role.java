package com.example.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@Builder
@ToString(exclude = "users")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Role extends BaseEntity {
    @Column(name = "label")
    private String label;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            inverseJoinColumns = { @JoinColumn(name = "user_id") },
            joinColumns = { @JoinColumn(name = "role_id") }
    )
    @JsonIgnore
    private Set<User> users = new HashSet<>();
}
