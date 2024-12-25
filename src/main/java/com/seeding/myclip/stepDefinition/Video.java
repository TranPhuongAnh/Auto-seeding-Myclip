package com.seeding.myclip.stepDefinition;

import com.seeding.myclip.config.AppiumDriverManager;
import com.seeding.myclip.config.CommonMethod;
import com.seeding.myclip.config.ConfigFileReader;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriverException;

public class Video extends AppiumDriverManager {
    AndroidDriver<MobileElement> driver = null;
    ConfigFileReader config = new ConfigFileReader();
    private String udid = config.getDriverSeri();
    CommonMethod common = new CommonMethod(getDriver(udid));


    @Given("Open the app login screen")
    public void open_the_app_login_screen(){
        System.out.println(udid);
        Video.getInstance().ImplicitlyWait_Config();
        String owb = null;
        try {
            driver = getDriver(udid);
            if (common.getCurentActivity().equals("http://meclip.vn/")){
                logout_app();
            }
            owb = "Browser opened successfully";
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
