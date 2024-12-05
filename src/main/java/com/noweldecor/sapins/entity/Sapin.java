package com.noweldecor.sapins.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Sapin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "sapin_decorations",
            joinColumns = @JoinColumn(name = "sapin_id"),
            inverseJoinColumns = @JoinColumn(name = "decoration_id")
    )
    private List<Decoration> decorations;

    @Column(name = "vendu", nullable = false)
    private boolean vendu;
}
