package cz.upce.eshop.ui;

import cz.upce.eshop.datafactory.Creator;
import cz.upce.eshop.entity.Product;
import cz.upce.eshop.repository.ProductRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProductUITest {

    private WebDriver driver;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    Creator creator;

    @BeforeAll
    public static void setupWebdriverChromeDriver() {
        String chromedriverPath = ProductUITest.class.getResource("/chromedriver").getFile();

        System.setProperty("webdriver.chrome.driver", chromedriverPath);
    }

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        productRepository.deleteAll();
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void addProductTest() {
        runOneProductTest();
    }

    @Test
    public void addProductTest2() {
        runOneProductTest();

    }

    @Test
    public void productListTest() {
        creator.saveEntities(
                product("Product1"),
                product("Product2"),
                product("Product3")
        );

        driver.get("http://localhost:8080/");
        assertProductInListVisible("Product1");
        assertProductInListVisible("Product2");
        assertProductInListVisible("Product3");

    }

    private Product product(String productName) {
        Product product = new Product();
        product.setProductName(productName);
        return product;
    }

    private void runOneProductTest() {
        driver.get("http://localhost:8080/product-form");
        driver.findElement(By.id("productName")).sendKeys("Nůžky");
        driver.findElement(By.id("image")).sendKeys("/home/pavel/Downloads/scissors.jpg");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        Assert.assertEquals(1, driver.findElements(By.xpath("//h2[text()='Product list']")).size());
        assertProductInListVisible("Nůžky");
    }

    private void assertProductInListVisible(String productName) {
        Assert.assertEquals(1, driver.findElements(By.xpath("//h3[text()='" +
                productName +
                "']")).size());
    }
}