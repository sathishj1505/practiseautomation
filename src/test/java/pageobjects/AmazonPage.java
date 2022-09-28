package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import utils.CommonUtils;

import java.util.concurrent.TimeUnit;

/*
@className = AmazonPage
@description = AmazonPage is page object factory for web elements and reusable functions for Amazon web application
@parameters = WedDriver
*/
public class AmazonPage {
    WebDriver driver;

    public AmazonPage(WebDriver driver) {
        this.driver = driver;
    }

    CommonUtils commonUtils = new CommonUtils(driver);
    /*Pabe objects related to Amazon web Page*/
    By hamburgerIcon = By.id("nav-hamburger-menu");
    By shopByCategoryTitle = By.xpath("//div[contains(text(),'shop by category')]");
    By tvApplianceSection = By.xpath("//div[contains(text(),'TV, Appliances, Electronics')]");
    By televisionsSection = By.xpath("//li/a[contains(text(),'Televisions')]");
    By brandSamsungCheckBox = By.xpath("//span[contains(text(),'Samsung')]//parent::a/div/label/input");
    By filterResultsDropdown = By.id("s-result-sort-select");
    By secondHighestTelevision = By.xpath("//div[@data-index=2]");
    By aboutThisItem = By.xpath("//h1[contains(text(),'About this item')]");
    By aboutThisItemText = By.xpath("//div[@id='feature-bullets']");


    /*
    @methodName = verifyTelevision
    @description = This method is test steps from Amazon homepage to televison about this item verification
    */
    public void verifyAmazonTelevision() throws InterruptedException {
        commonUtils.clickElement(hamburgerIcon);
        commonUtils.elementPresent(shopByCategoryTitle);
        commonUtils.clickElement(tvApplianceSection);
        commonUtils.clickElement(televisionsSection);
        TimeUnit.SECONDS.sleep(5);
        commonUtils.scrollToElement(brandSamsungCheckBox);
        commonUtils.actionClick(brandSamsungCheckBox);
        commonUtils.selectFromDropdown(filterResultsDropdown,"price-desc-rank");
        commonUtils.clickElement(secondHighestTelevision);
        commonUtils.switchToTab();
        //Asserting that About This Item Section is present
        commonUtils.elementPresent(aboutThisItem);
        commonUtils.scrollToElement(aboutThisItem);
        Reporter.log(commonUtils.getText(aboutThisItemText));
    }

}
