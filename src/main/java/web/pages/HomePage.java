package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.components.Product;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static com.zebrunner.carina.utils.common.CommonUtils.pause;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        driver.get("https://demoblaze.com/");
        pause(1);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(d -> d.findElements(By.cssSelector("div[class='card h-100']")));
    }

    public List<Product> getProducts() {
        return driver.findElements(By.cssSelector("div[class='card h-100']"))
                .stream()
                .map(e -> new Product(e))
                .collect(Collectors.toList());
    }

    public List<String> getNavItems() {
        return driver.findElements(By.xpath("//li[contains(@class, 'nav-item')]/a[not(contains(@style,'display:none'))]"))
                .stream()
                .map(e -> e.getText())
                .collect(Collectors.toList());
    }

    public CartPage clickNavBar(String name) {
        driver.findElement(By.xpath(String.format("//li[contains(@class, 'nav-item')]/a[text()='%s']", name))).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> d.findElement(By.xpath("//tr[@class='success']")));
        return new CartPage(driver);
    }

    public void selectCategory(String categoryName) {
        driver.findElement(By.xpath(String.format("//a[text()='CATEGORIES']/../a[text()='%s']", categoryName))).click();
    }

    public ProductCardPage clickCard(int cardNumber) {
        driver.findElements(By.xpath(".//h4/a")).get(cardNumber).click();
        pause(1);
        return new ProductCardPage(driver);
    }

//    public void clickNavBar() {
//        getNavItems().
//
//                get(cardNumber).click();
//        pause(1);
//        return new ProductCardPage(driver);
//    }

}