package com.zagora17.secondhandclothes.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.zagora17.secondhandclothes.dao.CustomerRepository;
import com.zagora17.secondhandclothes.dto.PaymentInfoDTO;
import com.zagora17.secondhandclothes.dto.Purchase;
import com.zagora17.secondhandclothes.dto.PurchaseResponse;
import com.zagora17.secondhandclothes.entity.Address;
import com.zagora17.secondhandclothes.entity.Customer;
import com.zagora17.secondhandclothes.entity.Order;
import com.zagora17.secondhandclothes.entity.OrderItem;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository customerRepository;

    @Autowired
    CheckoutServiceImpl(CustomerRepository customerRepository,
                        @Value("${stripe.key.secret}") String secret ){
        this.customerRepository = customerRepository;

        Stripe.apiKey = secret;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        //retrieve order from DTO
        Order order = purchase.getOrder();

        //generate an order tracking number
        order.setOrderTrackingNumber(generateOrderTrackingNumber());

        //fulfill order with order items
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(order::add);

        //populate shipping and billing addresses into order

        order.setShippingAddress(purchase.getShippingAddress());
        order.setBillingAddress(purchase.getBillingAddress());

        //populate customer with order

        Customer customer = purchase.getCustomer();

        // check if this is an existing customer
        String theEmail = customer.getEmail();

        Customer customerFromDB = customerRepository.findByEmail(theEmail);
        if (customerFromDB != null) {
            customer = customerFromDB;
        }
        customer.addOrder(order);

        //save to the database
        customerRepository.save(customer);

        return new PurchaseResponse(order.getOrderTrackingNumber());

    }

    @Override
    public PaymentIntent createPaymentIntent(PaymentInfoDTO paymentInfoDTO) throws StripeException {
        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        Map<String, Object> params = new HashMap<>();

        params.put("amount", paymentInfoDTO.getAmount());
        params.put("currency", paymentInfoDTO.getCurrency());
        params.put("payment_method_types", paymentMethodTypes);

        return PaymentIntent.create(params);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
