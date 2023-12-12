package TH_StudentForm.Actions.PageObject;

import TH_Login.Actions.Commons.BasePage;
import TH_StudentForm.Interfaces.InputPageUI;
import org.openqa.selenium.WebDriver;
import java.util.HashMap;

public class InputPageObject {
    WebDriver driver;
    private static BasePage basePage = new BasePage();

//    public InputPageObject(WebDriver driver) {
//        this.driver = driver;
//    }
//    public void inputStudentInfo(HashMap<String, String> data) {
//        basePage.sendKeyToElement(driver, InputPageUI.xpath1, data.getOrDefault("fullName", null));
//        basePage.sendKeyToElement(driver, InputPageUI.xpath2, data.getOrDefault("email", null));
//        basePage.sendKeyToElement(driver, InputPageUI.xpath3, data.getOrDefault("address", null));
//        basePage.sendKeyToElement(driver, InputPageUI.xpath4, data.getOrDefault("permanentAddress", null));
//        basePage.clickToElement(driver, InputPageUI.xpath5);
//    }
    //Excel
    public static void inputStudentInfoExcel(WebDriver driver, String fullName, String email, String address, String permanentAddress) {
        basePage.sendKeyToElement(driver, InputPageUI.xpath1, fullName);
        basePage.sendKeyToElement(driver, InputPageUI.xpath2, email);
        basePage.sendKeyToElement(driver, InputPageUI.xpath3, address);
        basePage.sendKeyToElement(driver, InputPageUI.xpath4, permanentAddress);
        basePage.clickToElement(driver, InputPageUI.xpath5);
    }
}
