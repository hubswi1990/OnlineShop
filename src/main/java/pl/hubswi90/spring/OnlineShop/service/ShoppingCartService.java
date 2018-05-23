package pl.hubswi90.spring.OnlineShop.service;

import pl.hubswi90.spring.OnlineShop.domain.Product;

import java.util.Map;

public interface ShoppingCartService {

    public void addProduct(Long productId);
    public void removeProduct(Long productId);
    public Map<Long, Product> getProductsInCart();
    public double getTotalPrice();
}
