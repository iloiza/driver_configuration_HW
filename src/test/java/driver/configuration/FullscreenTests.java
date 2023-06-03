package driver.configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class FullscreenTests {
    private Logger logger = LogManager.getLogger(FullscreenTests.class);
    private WebDriver driver;
    private final String LOGIN = "wirato4727@ippals.com";
    private final String PASSWORD = "Qwerty123!";

    @BeforeAll
    public static void beforeAll(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void before() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-fullscreen");
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
    public void taskThree() {
        driver.get("https://otus.ru");
        driver.findElement(By.cssSelector(".header3__button-sign-in")).click();
        WebDriverWait waiting = new WebDriverWait(driver, Duration.ofSeconds(10));
        waiting.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='text'][@placeholder='Электронная почта']")))
                .sendKeys(LOGIN);
        driver.findElement(By.xpath("//input[@type='password'][@placeholder='Введите пароль']"))
                .sendKeys(PASSWORD);
        driver.findElement(By.xpath("//div[@class='new-log-reg__body']/descendant::button[contains(text(), 'Войти')]")).click();
        Set<Cookie> cookie = driver.manage().getCookies();
        for (Cookie cookies : cookie) {
            logger.info("Name = " + cookies.getName() + ", " + "Value = " + cookies.getValue());

        }
    }
}
