package cz.upce.eshop;

import cz.upce.eshop.entity.Product;
import cz.upce.eshop.repository.ProductRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ProductRepositoryTest {

  @Autowired
  private ProductRepository productRepository;

  @Test
  void saveProductTest() {

    saveProduct();
    List<Product> all = productRepository.findAll();
    Assertions.assertThat(all.size()).isEqualTo(1);

  }

  private void saveProduct() {
    Product product = new Product();
    product.setProductName("MyProduct");
    productRepository.save(product);
  }

}
