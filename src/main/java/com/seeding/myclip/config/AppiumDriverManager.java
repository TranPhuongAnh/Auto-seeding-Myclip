package com.seeding.myclip.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppiumDriverManager {
//    private static final String CHROME_DRIVER = "webdriver.chrome.driver"; //Cấu hình biến trỏ đến file webDriver cứng, selenium v4 trở đi k cần webDriver cứng
    private static final AppiumDriverManager appiumDriverManager = new AppiumDriverManager();
    private static AndroidDriver driver;
    private static String driverSeri;

    //Hàm sử dụng được tất cả hàm của class WebDriverManager
    public static AppiumDriverManager getInstance() {
        return appiumDriverManager;
    }

    //Hàm chờ theo thời gian trong file config
    public void ImplicitlyWait_Config() {
        long time = FileReaderManager.getInstance().getConfigReader().getImplicitlyWait();
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    //Hàm đặt biến cho giá trị cấu hình từ file config
    public AppiumDriverManager() {
        driverSeri = FileReaderManager.getInstance().getConfigReader().getDriverSeri();
    }

    // Hàm getDriver() khởi tạo diver
    protected AndroidDriver getDriver(){
        if (driver == null){
            driver = createDriver();
        }
        return driver;
    }

    // Hàm createDriver() tạo môi trường appium
    private AndroidDriver createDriver(){
        try {
            UiAutomator2Options options = uiAu_options();
            // Setup the Appium server URL to connect to
            URL appiumServer = new URL("http://127.0.0.1:4723/wd/hub/");

            driver = new AndroidDriver(appiumServer, options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    // Hàm lấy cấu hình emulator
    private DesiredCapabilities Emulator() {
        // Set DesiredCapabilities to send to Appium server
        DesiredCapabilities desi_cap = new DesiredCapabilities();
        desi_cap.setCapability("appium:platformName", "Android");
        desi_cap.setCapability("appium:platformVersion", "16.0");
        desi_cap.setCapability("appium:automationName", "UiAutomator2");
        desi_cap.setCapability("appium:udid", driverSeri);
//        desi_cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixcel 9 API 35");
        desi_cap.setCapability("appium:appPackage", "com.viettel.myclip");
        desi_cap.setCapability("appium:appActivity", "com.viettel.myclip.MainActivity");
        desi_cap.setCapability("appium:noReset", false);
        desi_cap.setCapability("appium:ignoreHiddenApiPolicyError", false);
        desi_cap.setCapability("appium:appWaitActivity", "*");
//        desi_cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);

        return desi_cap;
    }

    // Hàm cấu hình option thay cho emulator
    private UiAutomator2Options uiAu_options(){
        UiAutomator2Options options = new UiAutomator2Options();
//        options.setDeviceName("emulator-5554");
        options.setPlatformName("Android");
        options.setPlatformVersion("16.0");
        options.setAutomationName("UiAutomator2");
        options.setUdid(driverSeri);
        options.setAppPackage("com.viettel.myclip");
        options.setAppActivity("com.viettel.myclip.MainActivity");
        options.setNoReset(false);
        options.setIgnoreHiddenApiPolicyError(false);
        options.setAppWaitActivity("*");

        return options;
    }

    // Hàm đóng appium driver
    public void CloseDriver() {
        try
        {
            Thread.sleep(1000);
            driver.quit();
        }
        catch(Exception exp) {
            System.out.println(exp.getMessage());
//            System.out.println(exp.getCause());
//            exp.printStackTrace();
        }
    }
}
