package com.seeding.myclip.config;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppiumDriverManager {
//    private static final String CHROME_DRIVER = "webdriver.chrome.driver"; //Cấu hình biến trỏ đến file webDriver cứng, selenium v4 trở đi k cần webDriver cứng
    private static final AppiumDriverManager appiumDriverManager = new AppiumDriverManager();
    public static CommonMethod commonMethod;
    public static ConfigFileReader configFileReader;
    private static AndroidDriver<MobileElement> driver;

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
    public AppiumDriverManager() {}

    // Hàm getDriver() khởi tạo diver
    protected AndroidDriver<MobileElement> getDriver(String udid){
        if (driver == null){
            driver = createDriver(udid);
        }
        return driver;
    }

    // Hàm createDriver() tạo môi trường appium
    private AndroidDriver<MobileElement> createDriver(String udid){
        try {
            DesiredCapabilities caps = Emulator(udid);
            // Setup the Appium server URL to connect to
            URL appiumServer = new URL("http://127.0.0.1:4723/wd/hub/");

            driver = new AndroidDriver<MobileElement>(appiumServer, caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    // Hàm lấy cấu hình emulator
    private DesiredCapabilities Emulator(String udid) {
        // Set DesiredCapabilities to send to Appium server
        DesiredCapabilities desi_cap = new DesiredCapabilities();
        desi_cap.setCapability("appium:platformName", "Android");
//        desi_cap.setCapability("appium:platformVersion", "15.0");
        desi_cap.setCapability("appium:automationName", "UiAutomator2");
        desi_cap.setCapability("appium:udid", udid);
//        desi_cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixcel 9 API 35");
        desi_cap.setCapability("appium:appPackage", "com.viettel.myclip");
        desi_cap.setCapability("appium:appActivity", "com.viettel.myclip.MainActivity");
        desi_cap.setCapability("appium:noReset", false);
        desi_cap.setCapability("appium:ignoreHiddenApiPolicyError", false);
        desi_cap.setCapability("appium:appWaitActivity", "*");

        return desi_cap;
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
