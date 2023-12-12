package TH_Login.TestCases;

import TH_Login.Actions.Commons.BaseTest;
import TH_Login.Actions.PageObject.HomePageObject;
import TH_Login.Actions.PageObject.LoginPageObject;
import TH_Login.Utilities.Helpers.ExcelHelpers;
import TH_Login.Utilities.Listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class TC02_LoginLogout_Excel extends BaseTest {
    private WebDriver driver;
    private LoginPageObject login;

    @BeforeClass
    public void setUp() {
        driver = getDriver();
    }

    @Test (priority = 0)
    public void Test_Login() throws Exception{
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile("src/test/java/TH_Login/TestData/loginData.xlsx", "Sheet1");
        for (int i = 1; i < 4; i++){
            login.Login(driver,
                    excel.getCellData("username", i),
                    excel.getCellData("password", i));
            HomePageObject.Logout(driver);
        }
    }

    @AfterClass
    public void afterTest() throws Exception{
        tearDown();
    }
}
