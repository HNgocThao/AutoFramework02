package BT_Create3Event.Actions.PageObject;

import BT_Create3Event.Interfaces.EventsPageUI;
import BT_Create3Task.Interfaces.TaskPageUI;
import TH_Login.Actions.Commons.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;


public class EventsPageObject {
    WebDriver driver;
    private static BasePage basePage = new BasePage();

    public EventsPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void AddEvent(HashMap<String, String> data) {
        basePage.isElementDisplayed(driver, EventsPageUI.Events_txt);
        basePage.clickToElement(driver, EventsPageUI.Events_txt);
        basePage.clickToElement(driver, EventsPageUI.AddEvents_txt);
        basePage.sendKeyToElement(driver, EventsPageUI.Title_txb, data.getOrDefault("title", null));
        basePage.sendKeyToElement(driver, EventsPageUI.Startdate_txb, data.getOrDefault("start_date", null));
        basePage.clickToElement(driver, EventsPageUI.Save_btn);
        basePage.clickToElement(driver, EventsPageUI.Close_btn);
    }
    public static void CteateEventExcel(WebDriver driver, String title, String start_date) {
        basePage.isElementDisplayed(driver, EventsPageUI.Events_txt);
        basePage.clickToElement(driver, EventsPageUI.Events_txt);
        basePage.clickToElement(driver, EventsPageUI.AddEvents_txt);
        basePage.sendKeyToElement(driver, EventsPageUI.Title_txb, title);
        basePage.sendKeyToElement(driver, EventsPageUI.Startdate_txb, start_date);
        basePage.clickToElement(driver, EventsPageUI.Save_btn);
        basePage.clickToElement(driver, EventsPageUI.Close_btn);

    }
}
