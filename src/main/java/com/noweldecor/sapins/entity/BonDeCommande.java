package com.noweldecor.sapins.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BonDeCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "adresse", nullable = false)
    private String adresse;

    @Column(name = "coutTotal", nullable = false)
    private int coutTotal;

    @Column(name = "poidsTotal", nullable = false)
    private int poidsTotal;

    @OneToOne
    @JoinColumn(name = "sapin_id", nullable = false)
    private Sapin sapin;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}

