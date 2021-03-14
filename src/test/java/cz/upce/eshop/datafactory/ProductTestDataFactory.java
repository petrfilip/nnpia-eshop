package cz.upce.eshop.datafactory;


import cz.upce.eshop.entity.Product;
import cz.upce.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductTestDataFactory {

    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(String productName) {
        Product product = new Product();
        product.setProductName(productName);
        productRepository.save(product);
    }
}
