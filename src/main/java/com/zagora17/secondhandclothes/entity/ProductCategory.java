package com.zagora17.secondhandclothes.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "product_categories")
@Getter
@Setter
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "category_name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Set<Product> products;

    @ManyToOne
    @JoinColumn(name = "super_category_id", nullable = false)
    private SuperCategory superCategory;

    @Transient
    private Long superCategoryID;

    @PostLoad
    public void setSuperCategoryID() {
        this.superCategoryID = superCategory.getId();
    }

}
