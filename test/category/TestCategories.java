package category;

import framework.Helper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import pages.CategoriesPage;

public class TestCategories {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @BeforeClass
    public static void setUpClass() {

//        dateFormat = new SimpleDateFormat("HH:mm:ss") ;
        System.out.println("@BeforeClass: " + dateFormat.format(new Date()));

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        driver.manage().window().maximize();
        driver.get("http://bvtest.school.cubes.rs/login");

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        emailField.sendKeys("qa@cubes.rs");

        WebElement passwordField = driver.findElement(By.cssSelector("input[name='password']"));
        passwordField.sendKeys("cubesqa");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-primary")));
        loginButton.click();

        System.out.println("Page title is: " + driver.getTitle());
    }

    @AfterClass
    public static void tearDownClass() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("@AfterClass: " + dateFormat.format(new Date()));
        driver.quit();
    }

    @Before
    public void setUp() {
        
        WebElement categories = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='app-navbar-collapse']/ul[1]/li[3]/a")));
        categories.click();
        
        System.out.println("@Before: " + dateFormat.format(new Date()));
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("@After: " + dateFormat.format(new Date()));

    }

    @Test
    public void testCreateNewCategory() {

        for (int i = 0; i < 1; i++) {
            
            CategoriesPage categoriesPage = new CategoriesPage();
                    
//            categoriesPage.clickOnAddCategoryButton(driver);
            
//            WebElement addCategoryButton = driver.findElement(By.className("pull-right"));
//            addCategoryButton.click();
        
//              categoriesPage.sendTextOnTitleField(driver);

//            WebElement titleField = driver.findElement(By.id("title"));
//            titleField.sendKeys(Helper.getRandomText());

//              categoriesPage.clickOnSaveCategoryButton(driver);

//            WebElement saveButton = driver.findElement(By.id("save-category-button"));
//            saveButton.click();

            categoriesPage.addNewCategory(driver);

            String expectedUrl = "http://bvtest.school.cubes.rs/admin/categories";
            String actualUrl = driver.getCurrentUrl();

            Assert.assertEquals("Url se ne poklapa", expectedUrl, actualUrl);

            String expectedTitle = "Brze vesti admin  | Categories ".replaceAll("\\s+", " ").trim();
            System.out.println("expected title: '" + expectedUrl + "'");
            String actualTitle = driver.getTitle();
            System.out.println("actual title: '" + expectedTitle + "'");

            Assert.assertEquals("Title doesn't match", expectedTitle, actualTitle);
        }

    }

    @Test
    public void testEditLastCategory() {
        
        CategoriesPage categoriesPage = new CategoriesPage();
        categoriesPage.editLastCategory(driver , wait);
        
        
//            WebElement table = driver.findElement(By.id("categoriesTable"));
        
        String expectedUrl = "http://bvtest.school.cubes.rs/admin/categories";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals("Url se ne poklapa", expectedUrl, actualUrl);

        String expectedTitle = "Brze vesti admin  | Categories ".replaceAll("\\s+", " ").trim();
        System.out.println("expected title: '" + expectedUrl + "'");
        String actualTitle = driver.getTitle();
        System.out.println("actual title: '" + expectedTitle + "'");

        Assert.assertEquals("Title doesn't match", expectedTitle, actualTitle);
    }
    
    @Test 
    public void testEditFirstCategory() {
        CategoriesPage categoriesPage = new CategoriesPage();
        categoriesPage.editFirstCategory(driver , wait);

        Assert.assertEquals("Url title doesen't match", "http://bvtest.school.cubes.rs/admin/categories", driver.getCurrentUrl());
    }

    @Test
    public void testDeleteFirstCategory() {
        
        CategoriesPage categoriesPage = new CategoriesPage();
        categoriesPage.deleteFirstCategory(driver , wait);
        
    }
}
