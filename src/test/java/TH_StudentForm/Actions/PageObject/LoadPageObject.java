package TH_StudentForm.Actions.PageObject;

import TH_Login.Actions.Commons.BasePage;
import TH_StudentForm.Interfaces.LoadPageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class LoadPageObject {

    private static BasePage basePage = new BasePage();

    public static void LoadPage(WebDriver driver) {
        basePage.isElementDisplayed(driver, LoadPageUI.logoPath);
        basePage.clickToElement(driver, LoadPageUI.Elements_click);
        basePage.clickToElement(driver, LoadPageUI.Textbox_click);
    }
}
