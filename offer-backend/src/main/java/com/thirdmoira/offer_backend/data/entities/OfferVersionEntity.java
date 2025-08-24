package com.thirdmoira.offer_backend.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "offer_versions")
public class OfferVersionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name="offer_id")
    private OfferEntity offer;
    @Column(name = "title")
    private String title;
    @Column(name = "version_number")
    private Long versionNumber;
}
