package com.seeding.myclip.stepDefinition;

import com.seeding.myclip.config.AppiumDriverManager;
import com.seeding.myclip.config.CommonMethod;
import com.seeding.myclip.config.ConfigFileReader;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;

public class Video extends AppiumDriverManager {
    AndroidDriver<MobileElement> driver = null;
    ConfigFileReader config = new ConfigFileReader();
    private String udid = config.getDriverSeri();
    CommonMethod common = new CommonMethod(getDriver(udid));

    private By LoginScreen = By.id("com.viettel.myclip:id/action_bar_root");
    private By StartButton = By.xpath("//android.widget.TextView[@text='Bắt đầu']");
    private By CancelPopup = By.xpath("//android.widget.TextView[@text='Bỏ qua']");
    private By AccMenu = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[13]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[5]");
    private By LoginButton = By.xpath("//android.widget.TextView[@text='Đăng nhập']");

    @Given("Open the app login screen")
    public void open_the_app_login_screen(){
        System.out.println(udid);
        String owb = null;
        try {
            Video.getInstance().ImplicitlyWait_Config();
            driver = getDriver(udid);
            common.clickElement(StartButton);
            common.clickElement(CancelPopup);
            common.clickElement(AccMenu);

            common.clickElement(LoginButton);
            String curActivity = driver.currentActivity();
            System.out.println(curActivity);
            if (common.getCurentActivity().equals("http://meclip.vn/")){
                logout_app();
            }
            owb = "App login screen opened successfully";
            System.out.println(owb);
        } catch (WebDriverException e){
            owb = e.getMessage();
            System.out.println(owb);
            e.printStackTrace();
        }
    }

    public void logout_app(){

    }

    @And("Close app")
    public void close_app(){
        CloseDriver();
    }
}
