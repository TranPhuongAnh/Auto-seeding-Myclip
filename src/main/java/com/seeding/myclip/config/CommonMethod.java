package com.seeding.myclip.config;

//import io.appium.java_client.MobileElement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.en_lol.AN;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class CommonMethod extends AppiumDriverManager {
    static AndroidDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public CommonMethod(AndroidDriver driver) {
        CommonMethod.driver = driver;
        long time = FileReaderManager.getInstance().getConfigReader().getImplicitlyWait();
        wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        js = (JavascriptExecutor) driver;
    }


    /**
     * ---- CHECK GIAO DIỆN TƯƠNG TÁC VỚI PHẦN TỬ -----
     */
    //Check hiển thị phần tử
    public boolean checkDisplay(By element) {
        WebElement locator;
        try {
            locator = driver.findElement(element);
            return locator.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    //Check cho phép thao tác với phần tử
    public boolean checkEnable(By element) {
        WebElement locator;
        try {
            locator = driver.findElement(element);
            return locator.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // scroll tới phần tử
    public void scrollToElement(By element) {
        // chuyển định dạng của phần tử
        WebElement element_name = driver.findElement(element);
        // Scroll tới phần tử
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element_name);
    }


    /**
     * --- NHÓM THAO TÁC VỚI CHUỘT ---
     */
    // Nhấn đúp chuột vào phần tử
    public void shouldDoubleClickElement(By element) {
        WebElement dblClick = driver.findElement(element);
        Actions actions = new Actions(driver);
        actions.doubleClick(dblClick).perform();
    }

    // Nhấn chuột phải vào phần tử
    public void shouldContextClick(By element) {
        WebElement dblClick = driver.findElement(element);
        Actions actions = new Actions(driver);
        actions.contextClick(dblClick).perform();
    }


    /**
     * --- Hàm hover chuột
     */
    //Hover vào 1 đối tượng
    public void hover(By e){
        WebElement hover_e = driver.findElement(e);
        Actions actions = new Actions(driver);
        actions.moveToElement(hover_e).perform();
    }

    //Hover đối tượng A và click đối tượng B
    public void hoverAndClick(By A, By B){
        WebElement hover_A = driver.findElement(A);
        WebElement click_B = driver.findElement(B);
        Actions actions = new Actions(driver);
        actions.moveToElement(hover_A).click(click_B).build().perform();
//        actions.moveToElement(hover_A).build().perform(); //Hai dòng 97-98 tác dụng tương tự dòng 96
//        click_B.click();
    }
    //Hover menu và action vào submenu (trường hợp hover vào A mà không kịp bắt B)
    public void hoverMenu(By Menu, By Submenu){
        WebElement menu = driver.findElement(Menu);
        WebElement submenu = driver.findElement(Submenu);
        Actions actions = new Actions(driver);
        actions.moveToElement(menu).perform();
        actions.moveToElement(submenu);
        actions.click();
        actions.perform();
    }

    /**
     * --- NHÓM THAO TÁC VỚI TEXT
     */
    // Get text từ lable
    public String getText(By element) {
        String text = driver.findElement(element).getText();
        return text;
    }

    // Get text từ input
    public String getValue(By element) {
        String text = driver.findElement(element).getAttribute("value");
        return text;
    }


    /**
     * --- NHÓM THAO TÁC CLICK
     */
    // Thực hiện click phần tử khi phần tử hiển thị trên DOM
    public void clickElement(By element) {
        driver.findElement(element).click();
    }

    // Thực hiện click phần tử khi phần tử không hiển thị trên DOM với JS
    public void clickElementHidden(By element) {
        WebElement element1 = driver.findElement(element);
        js.executeScript("arguments[0].click();", element1);
    }


    /**
     * --- NHÓM THAO TÁC LẤY THÔNG TIN URL
     */
    // Thực hiện lấy mã màn hình hiện tại
    public String getCurentActivity() {
        String activity = null;
//        if(driver instanceof AndroidDriver){
            activity = ((AndroidDriver) driver).currentActivity();
            System.out.println("Android current activity: " + activity);
//        }
//        else if (driver instanceof IOSDriver){
//            activity = ((IOSDriver) driver).getPageSource();
//            System.out.println("IOS current page source: " + activity);
//
//        }
        return activity;
    }


    /**
     * --- NHÓM THAO TÁC LẤY TÊN MIỀN ---
     */
    // Thực hiện lấy tên miền
    public String getDomainName(By element) {
        return js.executeScript("return document.domain;").toString();
    }

    // Thực hiện GET URL khi phần tử không hiển thị trên DOM với JS
    public String getURLHidden() {
        String IdUserChrome = js.executeScript("return document.URL;").toString();
        return IdUserChrome;
    }


    /**
     * --- NHÓM THAO TÁC DỮ LIỆU TRONG TRƯỜNG ---
     */
    // Hàm dùng chung điền dữ liệu vào element
    public void sendKeyElement(By element, String text) {
        driver.findElement(element).sendKeys(text);
    }


    // Hàm dùng chung điền dữ liệu vào element bị ẩn
    public void sendKeyElementHidden(By element, String text) {
        js.executeScript(element + ".setAttribute('value','" + text + "');");
    }

    // Hàm dùng chung xóa dữ liệu trong element
    public void clearTextElement(By element) {
        driver.findElement(element).clear();
    }

    //Hàm dùng chung chọn giá trị đầu tiên trong selectbox tĩnh
    public void selectStaticValue(By element) {
        WebElement element1 = driver.findElement(element);
        Select select = new Select(element1);
        select.selectByIndex(0);
    }

    // Hàm dùng chung chọn giá trị "option" trong combobox động
    public void selectDynamicValue(By element, By option) {
        WebElement element1 = driver.findElement(element);
        element1.click();
        WebElement option3 = driver.findElement(option);
        option3.click();
    }

    /**
     * Hàm lấy chuỗi * trong input Password theo chuỗi ký tự đã nhập
     */
    public String Password(String p, String kytu){
        int a = p.length();
        String string = "";
        if (a < 2){
            string = kytu;
        } else {
            for (int i = 0; i < a ; i++){
                string += kytu;
            }
        }
        return string;
    }

    /**
     * ---- HÀM DÙNG CHUNG THAO TÁC VỚI ALERT ----
     */
    // Alert: Thực hiện nhấn Đồng ý
    public void alertAccept() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    // Alert: Thực hiện nhấn Từ chối
    public void alertDismiss() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    //Messsage (flash/toast): Lấy nội dung message
    public String messageText(By e){
        long time = FileReaderManager.getInstance().getConfigReader().getImplicitlyWait();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(e));
        String textMess = message.getText();

        return textMess;
    }


    /**
     * THAO TÁC VỚI CHECKBOX
     */
    // Kiểm tra trạng thái checked của checkbox
    public String checkCheckboxStatus(By element) {
        String status;
        boolean b = true;
        if (driver.findElement(element).isSelected())
            status = "Checked";
        else status = "Uncheck";
        return status;
    }

    // Thực hiện checked checkbox
    public void clickCheckbox(By element) {
        driver.findElement(element).click();
    }

    // Thực hiện checked checkbox khi ẩn trên DOM
    public void checkedCheckboxHidden(By element) {
        js.executeScript("document.getElementById('id_of_element').checked=true;");
    }

    // Thực hiện uncheck checkbox khi ẩn trên DOM
    public void unCheckedCheckboxHidden(By element) {
        js.executeScript("document.getElementById('id_of_element').checked=false;");
    }

    // Thực hiện uncheck checkbox


    /**
     * THAO TÁC VỚI RADIO BUTTON
     */
    // Check radiobutton is checked
    public void checkCheckedRadioButton(By element) {
        driver.findElement(element).isSelected();
    }

    // Check radiobutton is Enable
    public void checkEnableRadioButton(By element) {
        driver.findElement(element).isEnabled();
    }

    // Check radiobutton is Disable
    public void checkDisableRadioButton(By element) {
        driver.findElement(element).isDisplayed();
    }

    // Click on radio button
    public void clickRadioButton(By element) {
        driver.findElement(element).click();
    }

    /**
     * Hàm đợi trang load xong rồi thao tác
     */
    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }

    /**
     * Hàm chụp ảnh màn hình TakesScreenShot của selenium
     * Chỉ có thể chụp toàn bộ giao diện driver
     */
    public void takeSnapShot(String fileWithPath){
        //Chuyển đổi đối tượng trình điều khiển web sang TakeScreenshot
        TakesScreenshot screenshot = ((TakesScreenshot)driver);

        //Gọi phương thức getScreenshotAs để tạo file ảnh
        File SrcFile=screenshot.getScreenshotAs(OutputType.FILE);

        // Di chuyển file ảnh tới đích mới
        File DestFile = new File(fileWithPath);

        // Sao chép tập tin tại đích
        try {
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}