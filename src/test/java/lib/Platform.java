package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Platform {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    // Это инстанс, специальный объект класса, который существует в единственном экземпляре
    // и не дает создать еще один экземпляр класса
    private static Platform instance;

    private Platform(){}

    public static Platform getInstance(){
        if (instance == null){
            instance = new Platform();
        }
        return instance;
    }


    public AppiumDriver getDriver() throws Exception
    {
        URL url = new URL(APPIUM_URL);
        if(this.isAndroid())
        {
            return new AppiumDriver(url, this.getAndroidDesiredCapabilities());
        }
        else if(this.isIOS())
        {
            return new AppiumDriver(url, this.getIOSDesiredCapabilities());
        }
        else
        {
            throw new Exception("Cannot get driver by platform from env variable. Platform value " + getPlatformVar());
        }
    }


    public boolean isAndroid()
    {
        return isPlatform(PLATFORM_ANDROID);
    }


    public boolean isIOS()
    {
        return isPlatform(PLATFORM_IOS);
    }


    private DesiredCapabilities getAndroidDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        //capabilities.setCapability("app", "C:\\Users\\yako\\IdeaProjects\\JavaAppiumAutomationYarDK\\apks\\org.wikipedia.apk");
        //capabilities.setCapability("app", "D:\\IdeaProjects\\JavaAppiumAutomationYarDK\\apks\\org.wikipedia.apk");
        capabilities.setCapability("app", "/Users/yaroslavkorotyshov/Desktop/JavaAppiumAutomationYarDKLessons/apks/org.wikipedia.apk");
        return capabilities;
    }


    private DesiredCapabilities getIOSDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("automationName","XCUITest");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE 11.4");
        capabilities.setCapability("platformVersion", "11.4");
        capabilities.setCapability("app", "/Users/yaroslavkorotyshov/Desktop/JavaAppiumAutomationYarDKLessons/apks/Wikipedia.app");
        return capabilities;
    }


    private boolean isPlatform(String my_platform)
    {
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }


    private String getPlatformVar()
    {
        return System.getenv("PLATFORM");
    }
}
