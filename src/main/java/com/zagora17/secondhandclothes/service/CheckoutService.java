package com.zagora17.secondhandclothes.service;

import com.zagora17.secondhandclothes.dto.Purchase;
import com.zagora17.secondhandclothes.dto.PurchaseResponse;

public interface CheckoutService {

    public PurchaseResponse placeOrder(Purchase purchase);
}
