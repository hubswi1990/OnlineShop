package pl.hubswi90.spring.OnlineShop.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.hubswi90.spring.OnlineShop.domain.Product;
import pl.hubswi90.spring.OnlineShop.repository.ProductRepository;
import pl.hubswi90.spring.OnlineShop.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository repository;


    @Transactional
    @Override
    public void saveProduct(Product p) {
        repository.saveProductInDatabase(p);
    }

    @Override
    public Product getProduct(long id) {
        return repository.getProoductFromDatabse(id);
    }

    @Transactional
    @Override
    public List<Product> getAllProduct() {
        return repository.getAllProductFromDatabse();
    }

    @Transactional
    @Override
    public void deleteProduct(long id) {
        repository.removeProductInDatabase(id);
    }

    @Transactional
    @Override
    public void updateProduct(Product product) {
        repository.updateProductInDatabase(product);
    }
}
