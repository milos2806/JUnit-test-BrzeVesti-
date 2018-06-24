package portal;

import framework.Helper;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestPortals {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeClass
    public static void setUpClass() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        driver.manage().window().maximize();
        driver.get("http://bvtest.school.cubes.rs/login");

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        emailField.sendKeys("qa@cubes.rs");

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        passwordField.sendKeys("cubesqa");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-primary")));
        loginButton.click();
        System.out.println("Page title is: " + driver.getTitle());
    }

    @AfterClass
    public static void tearDownClass() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    @Before
    public void setUp() {
        WebElement portals = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app-navbar-collapse\"]/ul[1]/li[5]/a")));
        portals.click();
        System.out.println("Page title is: " + driver.getTitle());
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);

    }

    @Test
    public void testCreateNewPortal() {

        for (int i = 0; i < 3; i++) {
            WebElement addPortalButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("pull-right")));
            addPortalButton.click();

            WebElement titleFieldPortal = driver.findElement(By.id("title"));
            titleFieldPortal.sendKeys(Helper.getRandomTextPor());

            WebElement urlField = driver.findElement(By.id("url"));
            urlField.sendKeys(Helper.getRandomUrl());

            Select dropDownRegion = new Select(driver.findElement(By.name("region_id")));
            dropDownRegion.selectByValue("550");

            WebElement saveButton = driver.findElement(By.id("save-portal-button"));
            saveButton.click();

            String expectedUrl = "http://bvtest.school.cubes.rs/admin/portals";
            String actualUrl = driver.getCurrentUrl();

            Assert.assertEquals("Url does not match.", expectedUrl, actualUrl);

            String expectedTitle = "Brze vesti admin  | Portals".replaceAll("\\s+", " ").trim();
            System.out.println("expected title: '" + expectedTitle + "'");
            String actualTitle = driver.getTitle();
            System.out.println("actual title: '" + actualTitle + "'");

            Assert.assertEquals("Title does not match.", expectedTitle, actualTitle);
        }
    }

    @Test
    public void testEditLastPortal() {
        WebElement tBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-sortable")));
        List<WebElement> rows = tBody.findElements(By.tagName("tr"));

        System.out.println("number of rows: " + rows.size());

        WebElement lastRow = rows.get(rows.size() - 1);

        WebElement editButton = lastRow.findElement(By.className("btn-default"));
        editButton.click();

        WebElement titleFieldPortal = driver.findElement(By.id("title"));
        titleFieldPortal.sendKeys(Helper.getRandomTextPor());

        WebElement urlField = driver.findElement(By.id("url"));
        urlField.sendKeys(Helper.getRandomUrl());

        Select dropDownRegion = new Select(driver.findElement(By.name("region_id")));
        dropDownRegion.selectByValue("550");

        WebElement saveButton = driver.findElement(By.id("save-portal-button"));
        saveButton.click();

        String expectedUrl = "http://bvtest.school.cubes.rs/admin/portals";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals("Url does not match.", expectedUrl, actualUrl);

        String expectedTitle = "Brze vesti admin  | Portals".replaceAll("\\s+", " ").trim();
        System.out.println("expected title: '" + expectedTitle + "'");
        String actualTitle = driver.getTitle();
        System.out.println("actual title: '" + actualTitle + "'");

        Assert.assertEquals("Title does not match.", expectedTitle, actualTitle);

    }

    @Test
    public void testDeleteFirstPortal() {

        WebElement tBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-sortable")));
        List<WebElement> rows = tBody.findElements(By.tagName("tr"));

        System.out.println("number of rows: " + rows.size());
        
        WebElement firstRow = rows.get(0);

        WebElement deleteButton = firstRow.findElement(By.cssSelector("button[title='Delete']"));
        deleteButton.click();
        
        WebElement confirmDelete = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"portalDeleteDialog\"]/div/div/div[3]/button[2]")));
        confirmDelete.click();
        
        String expectedUrl = "http://bvtest.school.cubes.rs/admin/portals";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals("Url se ne poklapa", expectedUrl, actualUrl);

        String expectedTitle = "Brze vesti admin  | Portals ".replaceAll("\\s+", " ").trim();
        System.out.println("expected title: '" + expectedUrl + "'");
        String actualTitle = driver.getTitle();
        System.out.println("actual title: '" + expectedTitle + "'");

        Assert.assertEquals("Title doesn't match", expectedTitle, actualTitle);

    }
}
