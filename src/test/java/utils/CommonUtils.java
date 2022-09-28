package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/*
@className = CommonUtils
@description = Common utilities and functions
*/
public class CommonUtils extends BaseTest {
    public CommonUtils(WebDriver driver){
        this.driver = driver;
    }
    public void clickElement(By elementpath){
        driver.findElement(elementpath).click();
        Reporter.log("Web element is clicked");
    }
    public void selectFromDropdown(By elementpath, String value){
        Select select = new Select(driver.findElement(elementpath));
        select.selectByValue(value);
        Reporter.log("Selected value from dropdown");
    }

    public void enterText(By elementpath, String value){
        driver.findElement(elementpath).sendKeys(value);
        Reporter.log("Text is enterted");
    }

    public void elementPresent(By elementpath){
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(driver.findElement(elementpath)));
        Assert.assertTrue(driver.findElement(elementpath).isDisplayed());
        Reporter.log("Element is displayed");
    }

    public void actionClick(By elementpath) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(driver.findElement(elementpath)));
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(elementpath)).click().build().perform();
        Reporter.log("Button is clicked");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    }

    public void selectTableElement(By elementpath, By row, By column) {
        WebElement basetable = driver.findElement(elementpath);
        WebElement tableRow = basetable.findElement(row);
        WebElement tableData = tableRow.findElement(column);
        Actions actions = new Actions(driver);
        actions.moveToElement(tableData).doubleClick().build().perform();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void scrollToElement(By elementpath){
        WebElement element = driver.findElement(elementpath);
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    public void switchToTab(){
        ArrayList tabs = new ArrayList (driver.getWindowHandles());
        driver.switchTo().window((String) tabs.get(1));
    }

    public String getText(By elementpath){
        String text = driver.findElement(elementpath).getText();
        return text;
    }
}
