package BT_Create3Event.TestCases;

import BT_Create3Event.Actions.PageObject.EventsPageObject;
import BT_Create3Task.Actions.PageObject.LoginPageObject;
import BT_Create3Task.Actions.PageObject.TaskPageObject;
import TH_Login.Actions.Commons.BaseTest;
import TH_Login.Utilities.Listeners.TestListener;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.HashMap;
@Listeners(TestListener.class)
public class TC01_CreateEvent extends BaseTest {
    private WebDriver driver;
    EventsPageObject event;
    @BeforeClass
    public void beforeClass() {
        driver = getDriver();
        event = new EventsPageObject(driver);
    }

    @Test( priority = 0)
    public void Test_Login(){
        LoginPageObject.Login(driver);
    }

    @Test( priority = 1, dataProvider = "RISE_Login", dataProviderClass = TH_Login.Utilities.Helpers.PropertiesFile.class)
    @Description("Verify the login function")
    public void Test_CreateEvent(HashMap<String, String> data) {
        event.AddEvent(data);
    }

    @AfterClass
    public void afterTest() throws Exception{
        tearDown();
    }

}
