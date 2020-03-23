package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class CoreTestCase extends TestCase {

    protected RemoteWebDriver driver;

    /*
    private AppiumDriverLocalService appium_server = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
            .withAppiumJS(new File("/Applications/Appium.app/Contents/Resources/app/node_modules/appium/build/lib/main.js"))
            .usingPort(4723).withIPAddress("127.0.0.1"));
    */

    @Override
    protected void setUp() throws Exception
    {
        //appium_server.start();

        super.setUp();
        driver = Platform.getInstance().getDriver(); // Обращаемся к классу Platform, внутри создается единственный экземпляр класса и на основе это экземпляра мы строим драйвер
        this.rotateScreenPortrait();
        this.skipWelcomPageForIOSApp();
        this.openWikiWebPageForMobileWeb();

    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
        driver.quit();
        //appium_server.stop();
    }

    protected void rotateScreenPortrait()
    {
        if(driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform - " + Platform.getInstance().getPlatformVar());
        }

    }

    protected void rotateScreenLandscape()
    {
        if(driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenLandscape() does nothing for platform - " + Platform.getInstance().getPlatformVar());
        }

    }

    protected void backgroundApp(int seconds)
    {
        if(driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofMillis(seconds));
        } else {
            System.out.println("Method backgroundApp() does nothing for platform - " + Platform.getInstance().getPlatformVar());
        }

    }

    protected void openWikiWebPageForMobileWeb()
    {
        if (Platform.getInstance().isMW())
        {
            driver.get("https://en.m.wikipedia.org");
        } else {
            System.out.println("Method openWikiWebPageForMobileWeb() does nothing for platform - " + Platform.getInstance().getPlatformVar());
        }
    }

    private void skipWelcomPageForIOSApp(){
        if(Platform.getInstance().isIOS()){
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
            welcomePageObject.clickSkip();
        }
    }


}
