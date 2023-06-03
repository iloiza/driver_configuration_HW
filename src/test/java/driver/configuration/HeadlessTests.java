package driver.configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HeadlessTests {
    private WebDriver driver;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
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
        driver.findElement(By.cssSelector("input[aria-controls='listbox--searchbox_homepage']")).sendKeys("ОТУС");
        driver.findElement(By.cssSelector("button[aria-label='Search']")).click();
        Assertions.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным ...", driver
                .findElement(By.xpath("//article[@id='r1-0']/descendant::a[contains(@data-testid,'result-title-a')]/span"))
                .getText());
    }
}
