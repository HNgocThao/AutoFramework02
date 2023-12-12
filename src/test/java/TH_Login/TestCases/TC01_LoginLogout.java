package TH_Login.TestCases;

import TH_Login.Actions.Commons.BaseTest;
import TH_Login.Actions.PageObject.HomePageObject;
import TH_Login.Actions.PageObject.LoginPageObject;
import TH_Login.Utilities.Helpers.PropertiesFile;
import TH_Login.Utilities.Listeners.TestListener;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import java.util.HashMap;

@Listeners(TestListener.class)
public class TC01_LoginLogout extends BaseTest {
    private WebDriver driver;
    @BeforeClass
    public void setUp() {
        driver = getDriver();
    }

    @Test( priority = 0, dataProvider = "RISE_Login", dataProviderClass = PropertiesFile.class)
    @Description("Verify the login function")
    public void Test_Login(HashMap<String, String> data) {
        LoginPageObject.Login(driver, data.get("username"), data.get("password"));
        HomePageObject.Logout(driver);
    }
    @AfterClass
    public void afterTest() throws Exception{
        tearDown();
    }

}
