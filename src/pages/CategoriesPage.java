package pages;

import framework.Helper;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoriesPage {

    private void clickOnAddCategoryButton(WebDriver driver) {
        WebElement addCategoryButton = driver.findElement(By.className("pull-right"));
        addCategoryButton.click();
    }
    private void clickOnEditButton(WebElement row) {
        WebElement editButton = row.findElement(By.className("btn-default"));
//        WebElement editButton = lastRow.findElement(By.("button[title='Edit']"));
        editButton.click();
    }
    
    private void clickOnSaveCategoryButton(WebDriver driver) {
        WebElement clickOnSaveCategoryButton = driver.findElement(By.id("save-category-button"));
        clickOnSaveCategoryButton.click();
    }
    
    private void clickOnDeleteButton(WebElement row) {
        row.findElement(By.cssSelector("button[title='Delete']")).click();
    }
    
    private void clickOnConfirmDeleteButton(WebDriverWait wait) {
        WebElement confirmDelete = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"categoryDeleteDialog\"]/div/div/div[3]/button[2]")));
        confirmDelete.click();

    }
    
    private void sendTextOnTitleField(WebDriver driver) {
        WebElement sendTextOnTitleField = driver.findElement(By.id("title"));
        sendTextOnTitleField.sendKeys(Helper.getRandomText());
    }
    
     private void sendTextOnTitleFieldWithClear(WebDriver driver) {
        WebElement titleField = driver.findElement(By.id("title"));
        // titleField.clear(); ---> sluzi da ocisti title polje da bi mogli da unesemo novu vrednost
        titleField.clear();
        titleField.sendKeys(Helper.getRandomText());
    }
    
    private List<WebElement> getRowsFromTable(WebDriverWait wait) {
        WebElement tBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-sortable")));
        List<WebElement> rows = tBody.findElements(By.tagName("tr"));
        System.out.println("number of rows: " + rows.size());
        return rows;
    }

    private WebElement chooseLastRow(WebDriverWait wait) {
        List<WebElement> rows = getRowsFromTable(wait);
        return rows.get(rows.size() - 1);
    }

    private WebElement chooseFirstRow(WebDriverWait wait) {
        List<WebElement> rows = getRowsFromTable(wait);
        return getRowsFromTable(wait).get(0);
    }

    private WebElement chooseRandomRow(WebDriverWait wait) {
        List<WebElement> rows = getRowsFromTable(wait);
        WebElement randomRow = rows.get(Helper.getRandomIntiger(rows.size()));
        return randomRow;

    }

   

    /////////PUBLIC METHODS
    
    public void addNewCategory(WebDriver driver) {
        clickOnAddCategoryButton(driver);
        sendTextOnTitleField(driver);
        clickOnSaveCategoryButton(driver);
    }
    
    public void editLastCategory(WebDriver driver, WebDriverWait wait) {
        WebElement lastRow = chooseLastRow(wait);
        clickOnEditButton(lastRow);
        sendTextOnTitleField(driver);
        clickOnSaveCategoryButton(driver);

    }

    public void editFirstCategory(WebDriver driver, WebDriverWait wait) {
        WebElement firstRow = chooseFirstRow(wait);
        clickOnEditButton(firstRow);
        sendTextOnTitleField(driver);
        clickOnSaveCategoryButton(driver);
    }

    public void editRandomCategory(WebDriver driver, WebDriverWait wait) {
        WebElement randomRow = chooseRandomRow(wait);
        clickOnEditButton(randomRow);
        sendTextOnTitleField(driver);
        clickOnSaveCategoryButton(driver);
    }

    public void deleteFirstCategory(WebDriver driver, WebDriverWait wait) {
        WebElement firsRow = chooseFirstRow(wait);
        clickOnDeleteButton(firsRow);
        clickOnConfirmDeleteButton(wait);
    }
    
    public void deleteLastCategory(WebDriver driver, WebDriverWait wait) {
        WebElement lastRow = chooseLastRow(wait);
        clickOnDeleteButton(lastRow);
        clickOnConfirmDeleteButton(wait);
    }
    
    public void deleteRandomCategory(WebDriver driver, WebDriverWait wait) {
        WebElement randomRow = chooseRandomRow(wait);
        clickOnDeleteButton(randomRow);
        clickOnConfirmDeleteButton(wait);
    }
        
}
