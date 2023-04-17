package com.zagora17.secondhandclothes.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Order> orders;

    public void addOrder(Order order) {
        if (order != null) {
            if(orders == null) {
                orders = new HashSet<>();
            }
            order.setCustomer(this);
            orders.add(order);
        }
    }
}
