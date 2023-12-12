package BT_Create3Task.Actions.PageObject;

import BT_Create3Task.Interfaces.TaskPageUI;
import TH_Login.Actions.Commons.BasePage;
import TH_StudentForm.Interfaces.InputPageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import java.util.HashMap;


public class TaskPageObject {
    WebDriver driver;
    private static BasePage basePage = new BasePage();
    public TaskPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void AddTask(HashMap<String, String> data) {
        basePage.isElementDisplayed(driver, TaskPageUI.Tasks_txt);
        basePage.clickToElement(driver, TaskPageUI.Tasks_txt);
        basePage.clickToElement(driver, TaskPageUI.AddTasks_txt);
        basePage.sendKeyToElement(driver, TaskPageUI.Title_txb, data.getOrDefault("title", null));
        basePage.clickToElement(driver, TaskPageUI.Project_txt);
        basePage.sendKeyToElement(driver, TaskPageUI.Project_txt2, data.getOrDefault("project", null));
        driver.findElement(By.xpath(TaskPageUI.Project_txt2)).sendKeys(Keys.ENTER);
        basePage.clickToElement(driver, TaskPageUI.Save_btn);
        basePage.clickToElement(driver, TaskPageUI.Close_btn);
    }

    public static void CteateTaskExcel(WebDriver driver, String title, String project) {
        basePage.isElementDisplayed(driver, TaskPageUI.Tasks_txt);
        basePage.clickToElement(driver, TaskPageUI.Tasks_txt);
        basePage.clickToElement(driver, TaskPageUI.AddTasks_txt);
        basePage.sendKeyToElement(driver, TaskPageUI.Title_txb, title);
        basePage.clickToElement(driver, TaskPageUI.Project_txt);
        basePage.sendKeyToElement(driver, TaskPageUI.Project_txt2, project);
        driver.findElement(By.xpath(TaskPageUI.Project_txt2)).sendKeys(Keys.ENTER);
        basePage.clickToElement(driver, TaskPageUI.Save_btn);
        basePage.clickToElement(driver, TaskPageUI.Close_btn);

    }
}
