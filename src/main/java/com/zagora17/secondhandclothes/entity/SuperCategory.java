package com.zagora17.secondhandclothes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "super_categories")
@Getter
@Setter
class SuperCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "super_category_name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "superCategory")
    private Set<ProductCategory> productCategories;
}
