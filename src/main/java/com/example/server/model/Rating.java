package com.example.server.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ratings")
@Getter
@Setter
@Builder
@ToString(exclude = {"sender", "recipient"})
@NoArgsConstructor
@AllArgsConstructor
public class Rating extends BaseEntity {
    @Column(name = "label")
    private String label;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User recipient;
}
