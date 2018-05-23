package pl.hubswi90.spring.OnlineShop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import pl.hubswi90.spring.OnlineShop.domain.Product;
import pl.hubswi90.spring.OnlineShop.repository.ProductRepository;
import pl.hubswi90.spring.OnlineShop.service.ShoppingCartService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ProductRepository repo;

    private Map<Long, Product> products = new HashMap<Long, Product>();

    @Override
    public void addProduct(Long productId) {

        Product p = repo.getProoductFromDatabse(productId);
        if (products.containsKey(productId)) {
            products.replace(p.getId(), p);
        } else {
            products.put(p.getId(), p);
        }
    }

    @Override
    public void removeProduct(Long productId) {
        products.remove(productId);
    }

    @Override
    public Map<Long, Product> getProductsInCart() {
        return products;
    }

    @Override
    public double getTotalPrice() {
        double total = 0.0;
        for (Map.Entry<Long, Product> entry : products.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
            total += entry.getValue().getProductPrice();
        }

        return BigDecimal.valueOf(total)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue() ;
    }
}
