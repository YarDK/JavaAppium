package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_MOBILE_WEB = "mobile_web";
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


    public RemoteWebDriver getDriver() throws Exception
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
        else if(this.isMW())
        {
            return new ChromeDriver(this.getMWChromeOptions());
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

    public boolean isMW()
    {
        return isPlatform(PLATFORM_MOBILE_WEB);
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


    private ChromeOptions getMWChromeOptions()
    {
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);

        Map<String, Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");

        ChromeOptions chromeOptions = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver","/Applications/Google Chrome.app/Contents/MacOS/node_modules/chromedriver/lib/chromedriver/chromedriver");
        chromeOptions.addArguments("window-size=360,640");

        return chromeOptions;
    }



    private boolean isPlatform(String my_platform)
    {
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }


    public String getPlatformVar()
    {
        return System.getenv("PLATFORM");
    }
}
