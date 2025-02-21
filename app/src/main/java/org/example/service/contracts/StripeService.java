package org.example.service.contracts;

import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.Product;

public interface StripeService {
    Product createProduct(String name, String description) throws StripeException;
    Price createPrice(Long amount,String currency,String productId) throws StripeException;
}
