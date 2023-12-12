package BT_Create3Task.Actions.PageObject;

import BT_Create3Task.Interfaces.LoginPageUI;
import TH_Login.Actions.Commons.BasePage;
import org.openqa.selenium.WebDriver;

public class LoginPageObject {
    private static BasePage basePage = new BasePage();
    public static void Login(WebDriver driver){
        basePage.clickToElement(driver, LoginPageUI.Id_link);
        basePage.clickToElement(driver, LoginPageUI.Login_btn);
    }
}
