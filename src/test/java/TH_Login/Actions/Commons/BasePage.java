package TH_Login.Actions.Commons;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    //Chứa hàm dùng chung cho cả PageObject
    private WebDriver driver;
    private WebDriverWait wait;
    private int timeoutWaitForPageLoaded = 30;

    //constructor để lấy driver ben ngoài vào vì nội tại trong class này không tự tạo ra driver
//    public BasePage(WebDriver webDriver){
//        driver = webDriver;
//        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
//    }

    public void opeURL(WebDriver driver, String url){
        driver.get(url);
    }

    public String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }

    public String getPageURL(WebDriver driver){
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver){
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver){
        driver.navigate().back();
    }

    public void refreshCurrentPage(WebDriver driver){
        driver.navigate().refresh();
    }

    public void forwardToPage(WebDriver driver){
        driver.navigate().forward();
    }

    //methods for Alert
    //Wait xong tu switch
    public void acceptToAlert(WebDriver drive){
        //driver.switchTo().alert().accept();
        //Chờ 30 seconds và switch sang alert. Ổn định hơn command trên
        waitForAlertPresent(driver).accept();
    }

    public void cancelToAlert(WebDriver drive){
        //driver.switchTo().alert().dismiss();
        //Chờ 30 seconds và switch sang alert. Ổn định hơn command trên
        waitForAlertPresent(driver).dismiss();
    }

    public String getAlertText(WebDriver drive) {
        //return driver.switchTo().alert().getText();
        //Chờ 30 seconds và switch sang alert. Ổn định hơn command trên
        return waitForAlertPresent(driver).getText();
    }

    public void sendkeyToAlert(WebDriver drive, String key){
        //driver.switchTo().alert().sendKeys(key);
        //Chờ 30 seconds và switch sang alert. Ổn định hơn command trên
        waitForAlertPresent(driver).sendKeys(key);
    }

    public Alert waitForAlertPresent(WebDriver driver){
        return new WebDriverWait(driver,Duration.ofSeconds(30)).until((ExpectedConditions.alertIsPresent()));
    }

    //methods for Windown
    public void switchToWindowByID(WebDriver driver,String windowID){
        Set <String> allIDs=driver.getWindowHandles();
        for (String id:allIDs){
            if ((!id.equals(windowID))){
                driver.switchTo().window(id);
                sleepInSecond(2);// 2 seconds
                break;
            }
        }
    }

    private void sleepInSecond(long timeoutIsSecond) {
        try {
            Thread.sleep(timeoutIsSecond * 100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    public void switchToWindowByTitle(WebDriver driver,String expectedTitle){
        Set <String> allIDs=driver.getWindowHandles();
        for (String id:allIDs){
            driver.switchTo().window(id);
            String actualTitle= driver.getTitle();
            if (actualTitle.equals(expectedTitle)) {
                break;
            }

        }
    }

    //WebElement
    public By getByXpath(String xpathExpression){
        //Viết hàm này để tuân theo nguyên tắc dry
        //Get By.xpath từ 1 String
        return By.xpath(xpathExpression);
    }

    public WebElement getElement(WebDriver driver, String xpathExpression){
        //Viết hàm này để tuân theo nguyên tắc dry
        return driver.findElement(getByXpath(xpathExpression));
    }

    public void clickToElement(WebDriver driver, String xpathExpression){
        //Text() không làm viêc voi css, id, class thì không phải element nào cũng có
        //css chỉ có thể đi xuôi nhưng xpath đi ngược xui đều được
        //Button,link,checkbox,radio button, dropdown, image...
        //driver.findElement(By.xpath(xpathExpression)).click();
        //Theo nguyên tắc Dry
        getElement(driver,xpathExpression).click();
    }

    public void sendKeyToElement(WebDriver driver, String xpathExpression, String value){
        //Áp dụng textbox,textarea,dropdown list,upload file
        //driver.findElement(By.xpath(xpathExpression)).clear();
        //driver.findElement(By.xpath(xpathExpression)).sendKeys(value);
        getElement(driver,xpathExpression).clear();
        getElement(driver,xpathExpression).sendKeys(value);
    }

    public String getElementText(WebDriver driver, String xpathExpression){
        //return driver.findElement(By.xpath(xpathExpression)).getText();
        return getElement(driver,xpathExpression).getText();
    }

    public String getElementAtribute(WebDriver driver, String xpathExpression,String attributeName){
        return getElement(driver,xpathExpression).getAttribute(attributeName);
    }

    public String getElementCssValue(WebDriver driver, String xpathExpression,String propertiesName){
        return getElement(driver,xpathExpression).getCssValue(propertiesName);
    }

    //Chuyen màu rgb sang hexa
    public String getHexaColorByGRPA(WebDriver driver, String xpathExpression,String rgbColor){
        return Color.fromString(rgbColor).asHex().toUpperCase();
    }

    public List<WebElement> getListElement(WebDriver driver, String xpathExpression){
        return driver.findElements(By.xpath(xpathExpression));
    }

    //Get số lượng button, bao nhiêu column
    public int getListElementSize(WebDriver driver, String xpathExpression){
        return getListElement(driver,xpathExpression).size();
    }

    public void selectDropdown(WebDriver driver, String xpathExpression, String itemText){
//        Select select=new Select(getElement(driver,xpathExpression));
        //select.selectByVisibleText(itemText);
        // Dùng 1 lần nên không cần khai báo biến
        new Select(getElement(driver,xpathExpression)).selectByVisibleText(itemText);
    }

    public String getFirstSelectedOption(WebDriver driver, String xpathExpression){
        return new Select(getElement(driver,xpathExpression)).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String xpathExpression){
        return new Select(getElement(driver,xpathExpression)).isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String xpathExpression,String xpathChild,String expectedText){
        getElement(driver,xpathExpression).click();
        sleepInSecond(1);
        //Wait cho elements hiển thị sau đó đưa tất cả elements vào List
        List <WebElement> allItems=new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(xpathChild)));
        for (WebElement tempElement:allItems){
            String itemText=tempElement.getText();
            if (itemText.equals((expectedText))){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",tempElement);
                sleepInSecond(1);
                tempElement.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    public void checkToCheckboxRadio(WebDriver driver,String xpathExpression){
        if (!getElement(driver, xpathExpression).isSelected()){
            getElement(driver, xpathExpression).click();
        }
    }

    //Không dùng cho Radio, muốn uncheck radio thi check vào radio khác
    public void uncheckToCheckbox(WebDriver driver,String xpathExpression){
        if (getElement(driver, xpathExpression).isSelected()){
            getElement(driver, xpathExpression).click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver,String xpathExpression){
        return getElement(driver, xpathExpression).isDisplayed();
    }

    public boolean isElementSelected(WebDriver driver,String xpathExpression){
        return getElement(driver, xpathExpression).isSelected();
    }

    public boolean isElementEnabled(WebDriver driver,String xpathExpression){
        return getElement(driver, xpathExpression).isEnabled();
    }

    //Focus vào frame muốn
    public void switchToFrame(WebDriver driver,String xpathExpression){
        driver.switchTo().frame(getElement(driver, xpathExpression));
    }

    //Quay lại trang trước đó
    public void switchToDefaultContent(WebDriver driver){
        driver.switchTo().defaultContent();
    }

    //Actions
    //Bắt buộc sử dụng perform() cho tất cả methods của Action library
    public void hoverToElement(WebDriver driver,String xpathExpression){
        //Actions actions=new Actions(driver);
        //actions.moveToElement(getElement(driver, xpathExpression)).perform();
        new Actions(driver).moveToElement(getElement(driver, xpathExpression)).perform();
    }

    public void doubleClickToElement(WebDriver driver,String xpathExpression){
        new Actions(driver).doubleClick(getElement(driver, xpathExpression)).perform();
    }

    public void rightClickToElement(WebDriver driver,String xpathExpression){
        new Actions(driver).contextClick(getElement(driver, xpathExpression)).perform();
    }

    //Drag and Drop dùng cho HTML4, HTML5 không dùng được
    public void dragAnDropToElement(WebDriver driver,String sourceXpath,String targetXpath){
        new Actions(driver).dragAndDrop(getElement(driver, sourceXpath),getElement(driver, targetXpath)).perform();
    }

    //COMMAND trên MAC nhưng CTRL trên Windown
    public void sendKeyBoardToElement(WebDriver driver, String xpathExpression, Keys key){
        new Actions(driver).sendKeys(getElement(driver, xpathExpression),key).perform();
    }

    //JavascriptExecutor
    public Object executeForBrowser(WebDriver driver,String javaScript){
        return ((JavascriptExecutor) driver).executeScript(javaScript);
    }

    public String getInnerText(WebDriver driver){
        ////document.documentElement.innerText: Accesses the text content of the entire HTML document.
        return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
    }

    public boolean areExpectedTextInInnerText(WebDriver driver,String textExpected){
        //document.documentElement.innerText: Accesses the text content of the entire HTML document.
        //.match('"+ textExpected + "'): Uses the match method to search for the specified text (textExpected) within the document's text content. The result is an array of matches.
        //[0]: Retrieves the first element of the array (the entire matched string).
        String textActual=(String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.match('"+ textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public  void scrollToBottomPage(WebDriver driver){
        //"window.scrollBy(0, document.body.scrollHeight);": This JavaScript code scrolls the window vertically.
        //0: Horizontal scrolling. In this case, it's set to 0, meaning no horizontal scrolling is applied.
        //document.body.scrollHeight: Vertical scrolling. It scrolls the window by an amount equal to the height of the entire document body (scrollHeight represents the total height of an element's content, including content not visible on the screen).
        //((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, document.body.scrollHeight);");
    }
    public void navigateToUrlByJs(WebDriver driver,String url){
        //This JavaScript code sets the window.location property to a new URL specified by the url variable. It effectively redirects the browser to the provided URL.
        ((JavascriptExecutor) driver).executeScript("window.location='" +url+"'");
    }

    public void highlightElement(WebDriver driver,String xpathExpression){
        WebElement element=getElement(driver, xpathExpression);
        String originalStyle=element.getAttribute("style");
        //sets the style attribute of the specified element to the provided style (border: 2px solid red; border-style: dashed;).
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        // sets the style attribute of the specified element (arguments[0]) to the value provided as the second argument (arguments[1])
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1]);", element, originalStyle);

    }

    public void clickToElementByJS(WebDriver driver,String xpathExpression){
        //"arguments[0].click();": click() method on the specified element (arguments[0])
        //getElement(driver, xpathExpression): responsible for locating and returning a web element based on the provided XPath expression. It's used as the first argument (arguments[0]) to the click() method.
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",getElement(driver, xpathExpression));
        sleepInSecond(3);
    }
    public void scrollToElementOnTop(WebDriver driver,String xpathExpression){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",getElement(driver, xpathExpression));
    }
    public void scrollToElementOnDown(WebDriver driver,String xpathExpression){
        //"arguments[0].scrollIntoView(true);":invokes the scrollIntoView method on the specified element (arguments[0]). The true parameter indicates that the element should be scrolled into view aligning to the top of the viewport.
        //getElement(driver, xpathExpression): responsible for locating and returning a web element based on the provided XPath expression. It's used as the first argument (arguments[0]) to the scrollIntoView method.
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);",getElement(driver, xpathExpression));
    }

    public void setAttributeInDOM(WebDriver driver,String xpathExpression, String attributeName,String attributeValue){
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "','" + attributeValue +"');",getElement(driver, xpathExpression));
    }

    public void sendkeyToElementByJS(WebDriver driver,String xpathExpression, String value){
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value','" + value + "')", getElement(driver, xpathExpression));
    }

    public String getAttributeInDOM(WebDriver driver,String xpathExpression, String attributeName){
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName +"');", getElement(driver, xpathExpression));
    }

    public String getElementValidationMessage(WebDriver driver,String xpathExpression){
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;",getElement(driver, xpathExpression));
    }

    public boolean isImageLoaded(WebDriver driver,String xpathExpression){
        return  (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth >0",getElement(driver, xpathExpression));
    }

    //wait
    //Dùng cho getText
    public void waitForElementVisible(WebDriver driver,String xpathExpression){
        new WebDriverWait(driver,Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathExpression)));
    }

    //Wait thì nên dùng với By
    public void waitForListElementVisible(WebDriver driver,String xpathExpression){
        new WebDriverWait(driver,Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathExpression)));
    }

    public void waitForListElementClickable(WebDriver driver,String xpathExpression){
        new WebDriverWait(driver,Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(getByXpath(xpathExpression)));
    }

    public void waitForElementInvisible(WebDriver driver,String xpathExpression){
        new WebDriverWait(driver,Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathExpression)));
    }

    public void waitForAlertPresence(WebDriver driver,String xpathExpression){
        new WebDriverWait(driver,Duration.ofSeconds(15)).until(ExpectedConditions.alertIsPresent());
    }


    public void waitForPageLoaded(){
        // wait for jQuery to loaded
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        // wait for Javascript to loaded
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutWaitForPageLoaded));
            wait.until(jQueryLoad);
            wait.until(jsLoad);
        } catch (Throwable error) {
            Assert.fail("Quá thời gian load trang.");
        }

    }


}
