package pages;

import framework.Helper;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegionsPage {

    private void clickOnRegionButton(WebDriverWait wait) {
        WebElement addRegionButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("pull-right")));
        addRegionButton.click();
    }

    private void clickOnEditButton(WebElement row) {
        WebElement editButton = row.findElement(By.className("btn-default"));
//        WebElement editButton = lastRow.findElement(By.("button[title='Edit']"));
        editButton.click();
    }

    private void clickOnSaveRegionButton(WebDriverWait wait) {
        WebElement saveRegionButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("save-region-button")));
        saveRegionButton.click();
    }

    private void clickOnDeleteButton(WebElement row) {
        row.findElement(By.cssSelector("button[title='Delete']")).click();
    }

    private void clickOnConfirmDeleteButton(WebDriverWait wait) {
        WebElement confirmDelete = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"regionDeleteDialog\"]/div/div/div[3]/button[2]")));
        confirmDelete.click();
    }

    public void clickOnDisableButton(WebElement row) {
        row.findElement(By.cssSelector("button[title='Disable']")).click();
 
    }

    private void ConfirmDisableButton(WebDriverWait wait) {
        WebElement confirmDisable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"regionDisableDialog\"]/div/div/div[3]/button[2]")));
        confirmDisable.click();
    }

    public void clickOnEnableButton(WebElement row) {
        row.findElement(By.cssSelector("button[title='Enable']")).click();
    }

    private void ConfirmEnableButton(WebDriverWait wait) {
        WebElement confirmEnable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"regionEnableDialog\"]/div/div/div[3]/button[2]")));
        confirmEnable.click();
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

    private WebElement chooseFirstRow(WebDriverWait wait) {
        List<WebElement> rows = getRowsFromTable(wait);
        return getRowsFromTable(wait).get(0);
    }

    private WebElement chooseLastRow(WebDriverWait wait) {
        List<WebElement> rows = getRowsFromTable(wait);
        return rows.get(rows.size() - 1);
    }

    private WebElement chooseRandomRow(WebDriverWait wait) {
        List<WebElement> rows = getRowsFromTable(wait);
        WebElement randomRow = rows.get(Helper.getRandomIntiger(rows.size()));
        return randomRow;
    }

    public void clickOnEnableButton() {

    }

    /// PUBLIC METHODS
    public void createNewRegion(WebDriverWait wait, WebDriver driver) {
        clickOnRegionButton(wait);
        sendTextOnTitleField(driver);
        clickOnSaveRegionButton(wait);

    }

    public void editFirstRegion(WebDriver driver, WebDriverWait wait) {
        WebElement firstRow = chooseFirstRow(wait);
        clickOnEditButton(firstRow);
        sendTextOnTitleField(driver);
        clickOnSaveRegionButton(wait);
    }

    public void editLastRegion(WebDriver driver, WebDriverWait wait) {
        WebElement lastRow = chooseLastRow(wait);
        clickOnEditButton(lastRow);
        sendTextOnTitleField(driver);
        clickOnSaveRegionButton(wait);
    }

    public void editRandomRegion(WebDriver driver, WebDriverWait wait) {
        WebElement randomRow = chooseRandomRow(wait);
        clickOnEditButton(randomRow);
        sendTextOnTitleField(driver);
        clickOnSaveRegionButton(wait);
    }

    public void deleteFirstRegion(WebDriver driver, WebDriverWait wait) {
        WebElement firstRow = chooseFirstRow(wait);
        clickOnDeleteButton(firstRow);
        clickOnConfirmDeleteButton(wait);
    }

    public void deleteLastRegion(WebDriver driver, WebDriverWait wait) {
        WebElement lastRow = chooseLastRow(wait);
        clickOnDeleteButton(lastRow);
        clickOnConfirmDeleteButton(wait);
    }

    public void deleteRandomRegion(WebDriver driver, WebDriverWait wait) {
        WebElement randomRow = chooseRandomRow(wait);
        clickOnDeleteButton(randomRow);
        clickOnConfirmDeleteButton(wait);
    }

    public void disableFirstRegion(WebDriverWait wait) {
        WebElement firstRow = chooseFirstRow(wait);
        clickOnDisableButton(firstRow);
        ConfirmDisableButton(wait);
    }

    public void disableLastRegion(WebDriverWait wait) {
        WebElement lastRow = chooseLastRow(wait);
        clickOnDisableButton(lastRow);
        ConfirmDisableButton(wait);
    }

    public void disableRandomRegion(WebDriverWait wait) {
        WebElement randomRow = chooseRandomRow(wait);
        clickOnDisableButton(randomRow);
        ConfirmDisableButton(wait);
    }

    public void enableFirstRegion(WebDriverWait wait) {
        WebElement firstRow = chooseFirstRow(wait);
        clickOnEnableButton(firstRow);
        ConfirmEnableButton(wait);
    }

    public void enableLastRegion(WebDriverWait wait) {
        WebElement lastRow = chooseLastRow(wait);
        clickOnEnableButton(lastRow);
        ConfirmEnableButton(wait);
    }

}
