package BT_Create3Task.TestCases;

import BT_Create3Task.Actions.PageObject.LoginPageObject;
import BT_Create3Task.Actions.PageObject.TaskPageObject;
import TH_Login.Actions.Commons.BaseTest;
import TH_Login.Utilities.Helpers.ExcelHelpers;
import TH_Login.Utilities.Listeners.TestListener;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.HashMap;
@Listeners(TestListener.class)
public class TC02_CreateTask_Excel extends BaseTest {
    private WebDriver driver;
    TaskPageObject task;
    @BeforeClass
    public void beforeClass() {
        driver = getDriver();
    }

    @Test( priority = 0)
    public void Test_Login(){
        LoginPageObject.Login(driver);
    }

    @Test(priority = 1)
    public void Test_CreateTask() throws Exception{
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile("src/test/java/TH_Login/TestData/loginData.xlsx", "Sheet3");
        for (int i = 1; i < 6; i++){
            task.CteateTaskExcel(driver,
                    excel.getCellData("title", i),
                    excel.getCellData("project", i));
        }
    }

    @AfterClass
    public void afterTest() throws Exception{
        tearDown();
    }
}
