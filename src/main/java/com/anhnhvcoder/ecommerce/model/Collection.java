package com.anhnhvcoder.ecommerce.model;


import com.anhnhvcoder.ecommerce.enums.CollectionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String image;
    private String slug;
    @Enumerated(EnumType.STRING)
    private CollectionStatus status;
    @OneToMany(mappedBy = "collection")
    private List<CollectionItem> items;
    private LocalDate startDate;
    private LocalDate endDate;
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;

}
