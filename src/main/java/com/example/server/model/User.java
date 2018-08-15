package com.example.server.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@ToString(callSuper = true, exclude = {"roles", "ratingsRecipient", "ratingsSender", "tasks"})
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<Role> roles = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "recipient", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Rating> ratingsRecipient = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "sender", orphanRemoval = true)
    private List<Rating> ratingsSender = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Task> tasks = new ArrayList<>();

    public void update(final User changes) {
        mobileNumber = changes.getMobileNumber();
        roles = changes.getRoles();
    }
}
