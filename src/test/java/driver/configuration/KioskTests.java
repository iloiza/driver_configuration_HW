package driver.configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class KioskTests {
    private Logger logger = LogManager.getLogger(KioskTests.class);
    private WebDriver driver;
    @BeforeAll
    public static void beforeAll(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--kiosk");
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
    public void taskTwo() throws NoSuchElementException {
        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
        WebDriverWait waiting = new WebDriverWait(driver, Duration.ofSeconds(5));
        waiting.until(driver -> true);
        if (driver.findElements(By.cssSelector(".pp_pic_holder")).size() == 1)
            throw new NoSuchElementException("Pop-up exists, but shouldn't!");

        driver.findElement(By.xpath("//li[@data-id='id-2']")).click();
        driver.findElement(By.cssSelector(".pp_pic_holder"));
        logger.info("Pop-up exist.");
        }
    }



