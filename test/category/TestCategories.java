package category;

import framework.Helper;
import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCategories {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeClass
    public static void setUpClass() {

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        driver.manage().window().maximize();
        driver.get("http://bvtest.school.cubes.rs/login");
    }

    @AfterClass
    public static void tearDownClass() throws InterruptedException {
//        Thread.sleep(3000);
//        driver.quit();
    }

    @Before
    public void setUp() {

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        emailField.sendKeys("qa@cubes.rs");

        WebElement passwordField = driver.findElement(By.cssSelector("input[name='password']"));
        passwordField.sendKeys("cubesqa");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-primary")));
        loginButton.click();

        System.out.println("Page title is: " + driver.getTitle());

        WebElement categories = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='app-navbar-collapse']/ul[1]/li[3]/a")));
        categories.click();

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreateNewCategory() {
        
        for (int i = 0; i < 5; i++) {
            WebElement addCategoryButton = driver.findElement(By.className("pull-right"));
        addCategoryButton.click();

        WebElement titleField = driver.findElement(By.id("title"));
        titleField.sendKeys(Helper.getRandomText());

        WebElement saveButton = driver.findElement(By.id("save-category-button"));
        saveButton.click();

        String expectedUrl = "http://bvtest.school.cubes.rs/admin/categories";
        String actualUrl = driver.getCurrentUrl();
        
        Assert.assertEquals("Url se ne poklapa", expectedUrl, actualUrl);

        String expectedTitle = "Brze vesti admin  | Categories ".replaceAll("\\s+", " ").trim();
//        System.out.println("expected title: '" + expectedUrl + "'");
        String actualTitle = driver.getTitle();
//        System.out.println("actual title: '" + expectedTitle + "'");

        Assert.assertEquals("Title doesn't match", expectedTitle, actualTitle);
        }

        
    }
}
