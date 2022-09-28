package test;

import org.testng.annotations.Test;
import pageobjects.AmazonPage;
import utils.BaseTest;

/*
@className = AmazonTest
@description = Test Class for Amazon Application automated test
*/
public class AmazonTest extends BaseTest {
    AmazonPage amazonPage = new AmazonPage(driver);
    /*
    @testMethod = verifyCustomerContactForm
    @description = This test will verify about this item section in Television order
    */
    @Test(priority = 1)
    public void verifyAmazonTelevisionAboutThisItem() throws InterruptedException {
        amazonPage.verifyAmazonTelevision();
    }
}
