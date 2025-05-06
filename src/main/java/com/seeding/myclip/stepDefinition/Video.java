package com.seeding.myclip.stepDefinition;

import com.seeding.myclip.config.AppiumDriverManager;
import com.seeding.myclip.config.CommonMethod;
import com.seeding.myclip.config.ConfigFileReader;
import com.seeding.myclip.config.ExcelHelpers;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Video extends AppiumDriverManager {
    AndroidDriver driver = null;
    ConfigFileReader config = new ConfigFileReader();
    private String udid = config.getDriverSeri();
    CommonMethod common = new CommonMethod(getDriver());
    ExcelHelpers excel = new ExcelHelpers();
    WebDriverWait wait;

    // Onboarding
    private By StartButton = By.xpath("//android.widget.TextView[@text='Bắt đầu']");
    private By CancelPopup = By.xpath("//android.widget.TextView[@text='Bỏ qua']");
    private By AccMenu = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[16]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[5]");
    private By LoginButton = By.xpath("//android.widget.TextView[@text='Đăng nhập']");

    // Login Screen
    private By InputPhone = By.xpath("//android.widget.EditText[@text='Số điện thoại']");
    private By InputPass = By.xpath("//android.widget.EditText[@text='Mật khẩu']");
    private By Button_Submit = By.xpath("//android.widget.TextView[@text='Đăng nhập']");

    public void open_the_app_login_screen(){
        System.out.println(udid);
        String owb = null;
//        try {
            driver = getDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            wait.until(ExpectedConditions.visibilityOfElementLocated(StartButton));
            common.clickElement(StartButton);

            wait.until(ExpectedConditions.visibilityOfElementLocated(CancelPopup));
            common.clickElement(CancelPopup);

//            wait.until(ExpectedConditions.visibilityOfElementLocated(AccMenu));
            common.clickElement(AccMenu);

//            wait.until(ExpectedConditions.visibilityOfElementLocated(LoginButton));
            common.clickElement(LoginButton);

            String curActivity = common.getCurentActivity();
            System.out.println(curActivity);
            if (common.getCurentActivity().equals("http://meclip.vn/")){
                logout_app();
            }
            owb = "App login screen opened successfully";
            System.out.println(owb);
//        } catch (WebDriverException e){
//            owb = e.getMessage();
//            System.out.println(owb);
//            e.printStackTrace();
//        }
    }

    public String login_app(String sheetName, int rowNum) throws Exception {
        //Nhập thông tin từ file excel
        excel.setExcelFile(config.getDataPath(), sheetName);
        String phone = excel.getCell("phone", rowNum);
        String pass = excel.getCell("password", rowNum);
        System.out.println("Phone: " + phone + "\nPass: " + pass);

        fill_data(InputPhone, phone);
        fill_data(InputPass, pass);

        // Click button và login page
        common.clickElement(Button_Submit);
        common.waitForPageLoaded();

//        Assert.assertTrue(common.checkDisplay(Banner) == true, "Login không thành công");

        return phone;
    }

    public void fill_data(By element, String text) {
        driver = getDriver();
        try {
            Thread.sleep(1000); // Đợi 1 giây (1000 milliseconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        common.sendKeyElement(element, text);
    }

    public void logout_app(){

    }

    @Given("Run tool seeding")
    public void run_tool_seeding() throws Exception {
        String sh_1 = "Account";
        String sh_2 = "Video";

        int sum_row_1 = excel.getSumRow(config.getDataPath(), sh_1);
        List<String> urls = excel.readIDExcel(config.getDataPath(), sh_2);

        open_the_app_login_screen();
        login_app(sh_1, 1);
    }

    @And("Close app")
    public void close_app(){
        CloseDriver();
    }
}
