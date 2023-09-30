package com.zagora17.secondhandclothes.service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.zagora17.secondhandclothes.dto.PaymentInfoDTO;
import com.zagora17.secondhandclothes.dto.Purchase;
import com.zagora17.secondhandclothes.dto.PurchaseResponse;

public interface CheckoutService {

    public PurchaseResponse placeOrder(Purchase purchase);

    public PaymentIntent createPaymentIntent(PaymentInfoDTO paymentInfoDTO) throws StripeException;
}
