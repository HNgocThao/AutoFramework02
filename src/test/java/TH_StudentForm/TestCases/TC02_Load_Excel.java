package TH_StudentForm.TestCases;

import TH_Login.Actions.Commons.BaseTest;
import TH_Login.Actions.PageObject.HomePageObject;
import TH_Login.Utilities.Helpers.ExcelHelpers;
import TH_Login.Utilities.Listeners.TestListener;
import TH_StudentForm.Actions.PageObject.InputPageObject;
import TH_StudentForm.Actions.PageObject.LoadPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class TC02_Load_Excel extends BaseTest {
    private WebDriver driver;
    private InputPageObject input;

    @BeforeClass
    public void setUp() {
        driver = getDriver();
    }
    @Test ( priority = 0)
    public void Test_OpenPage(){
        LoadPageObject.LoadPage(driver);
    }
    @Test(priority = 0)
    public void Test_Login() throws Exception{
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile("src/test/java/TH_Login/TestData/loginData.xlsx", "Sheet2");
        for (int i = 1; i < 6; i++){
            input.inputStudentInfoExcel(driver,
                    excel.getCellData("fullname", i),
                    excel.getCellData("email", i),
                    excel.getCellData("address",i),
                    excel.getCellData("permanentAddress", i));
        }
    }

    @AfterClass
    public void afterTest() throws Exception{
        tearDown();
    }
}
