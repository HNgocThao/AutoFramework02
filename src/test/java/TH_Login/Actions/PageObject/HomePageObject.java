package TH_Login.Actions.PageObject;

import TH_Login.Actions.Commons.BasePage;
import TH_Login.Interfaces.HomePageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageObject {
    private static BasePage basePage = new BasePage();
    public static void Logout(WebDriver driver){
        basePage.isElementDisplayed(driver, HomePageUI.imgP);
        basePage.clickToElement(driver, HomePageUI.img_click);
        basePage.clickToElement(driver, HomePageUI.signout_btn);
    }
}