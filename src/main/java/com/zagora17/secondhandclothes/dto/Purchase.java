package com.zagora17.secondhandclothes.dto;

import com.zagora17.secondhandclothes.entity.Address;
import com.zagora17.secondhandclothes.entity.Customer;
import com.zagora17.secondhandclothes.entity.Order;
import com.zagora17.secondhandclothes.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
