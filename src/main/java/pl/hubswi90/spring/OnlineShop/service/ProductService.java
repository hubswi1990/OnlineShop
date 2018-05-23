package pl.hubswi90.spring.OnlineShop.service;

import pl.hubswi90.spring.OnlineShop.domain.Product;

import java.util.List;

public interface ProductService {

    public void saveProduct(Product p);
    public Product getProduct(long id);
    public List<Product> getAllProduct();
    public void deleteProduct(long id);
    public void updateProduct(Product product);

    public List<Product> getProductsInCategory(long id);
}
