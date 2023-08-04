package com.zagora17.secondhandclothes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="unit_price")
    private BigDecimal unitPrice;

    @Column(name="image_url")
    private String imageUrl;

    @Column(name="active")
    private boolean active;

    @CreationTimestamp
    @Column(name="date_created")
    private Date dateCreated;

    @UpdateTimestamp
    @Column(name="date_updated")
    private Date lastUpdated;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategory category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Image> images;

    public void addImage(Image image) {
        if (image != null) {
            if ( images == null) {
                images = new ArrayList<>();
            }
            images.add(image);
            image.setProduct(this);
        }
    }

}
