package com.example.server.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@Builder
@ToString(callSuper = true, exclude = {"user", "subTasks", "parent"})
@NoArgsConstructor
@AllArgsConstructor
public class Task extends BaseEntity {
    @Column(name = "label")
    private String label;

    @Column(name = "description")
    private String description;

    @Column(name = "evaluation")
    private short evaluation;

    @ManyToOne
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "parent", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Task> subTasks = new ArrayList<>();

    @ManyToOne
    private Task parent;
}
