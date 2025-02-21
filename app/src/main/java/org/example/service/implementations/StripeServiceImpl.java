package org.example.service.implementations;

import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import org.example.service.contracts.StripeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeServiceImpl implements StripeService {

    @Override
    public Product createProduct(String name, String description) throws StripeException {
        ProductCreateParams productParams = ProductCreateParams.builder()
                .setName(name)
                .setDescription(description)
                .build();

        return Product.create(productParams);
    }

    @Override
    public Price createPrice(Long amount, String currency, String productId) throws StripeException{

        PriceCreateParams priceCreateParams = PriceCreateParams.builder()
                .setProduct(productId)
                .setUnitAmount(amount)
                .setCurrency(currency)
                .build();

        return Price.create(priceCreateParams);
    }
}
