package com.zagora17.secondhandclothes.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.zagora17.secondhandclothes.dto.PaymentInfoDTO;
import com.zagora17.secondhandclothes.dto.Purchase;
import com.zagora17.secondhandclothes.dto.PurchaseResponse;
import com.zagora17.secondhandclothes.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
class CheckoutController {

    private CheckoutService checkoutService;
    @Autowired
    CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
        return checkoutService.placeOrder(purchase);
    }

    @PostMapping("/payment-intent")
    ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfoDTO paymentInfoDTO) throws StripeException {
        return ResponseEntity.ok(checkoutService.createPaymentIntent(paymentInfoDTO).toJson());
    }


}
