package TH_Login.Actions.PageObject;

import TH_Login.Actions.Commons.BasePage;
import TH_StudentForm.Interfaces.InputPageUI;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import TH_Login.Interfaces.LoginPageUI;

import java.util.HashMap;

public class LoginPageObject {
    WebDriver driver;
    private static BasePage basePage = new BasePage();

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public static void Login(WebDriver driver , String username, String password){
        basePage.sendKeyToElement(driver,LoginPageUI.Username_txt,username);
        basePage.sendKeyToElement(driver,LoginPageUI.Password_txt,password);
        basePage.clickToElement(driver,LoginPageUI.Login_btn);
    }

}
