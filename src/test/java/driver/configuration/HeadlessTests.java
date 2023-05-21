package driver.configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HeadlessTests {
    //Logger logger = LogManager.getLogger(HeadlessTests.class);
    private WebDriver driver;
@BeforeEach
        public void before(){
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    options.addArguments("--headless");
    driver = new ChromeDriver(options);
}
    @AfterEach
    public void after() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    @Test
    public void taskOne() {
        driver.get("https://duckduckgo.com/");
        driver.findElement(By.cssSelector("#search_form_input_homepage")).sendKeys("ОТУС");
        driver.findElement(By.cssSelector("#search_button_homepage")).click();
        Assertions.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным ...", driver.findElement(By.cssSelector("#r1-0 a[data-testid='result-title-a'] span")).getText());

    }
}
