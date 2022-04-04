package cz.upce.ushop.ui

import cz.upce.eshop.EshopApplication;
import cz.upce.eshop.datafactory.Creator;
import cz.upce.eshop.entity.Product;
import cz.upce.eshop.repository.ProductRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = [EshopApplication])
class ProductUITest {

    private WebDriver driver;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    Creator creator;

    @BeforeAll
    static void setupWebdriverChromeDriver() {
        String chromedriverPath = ProductUITest.class.getResource("/chromedriver").getFile();

        System.setProperty("webdriver.chrome.driver", chromedriverPath);
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        productRepository.deleteAll();
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void productListTest() {
        creator.saveEntities(
                new Product(productName: "Product1"),
                new Product(productName: "Product2"),
                new Product(productName: "Product3")
        );

        driver.get("http://localhost:8080/");
        assertProductInListVisible("Product1");
        assertProductInListVisible("Product2");
        assertProductInListVisible("Product3");

    }

    private void assertProductInListVisible(String productName) {
        Assert.assertEquals(1, driver.findElements(By.xpath("//h3[text()='" +
                productName +
                "']")).size());
    }

}