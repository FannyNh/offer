package com.thirdmoira.offer_backend.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "offer_sections")
public class OfferSectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "offer_version_id")
    private OfferVersionEntity offerVersion;

    @ManyToOne
    @JoinColumn(name = "parent_section_id")
    private OfferSectionEntity parentSection;

    @Column(name = "title")
    private String title;

    @Column(name = "position")
    private Integer position;

    @CreatedDate
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;
}
