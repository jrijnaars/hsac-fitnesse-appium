package nl.hsac.fitnesse.fixture.slim.mobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import nl.hsac.fitnesse.fixture.Environment;
import nl.hsac.fitnesse.fixture.slim.web.SeleniumDriverSetup;
import nl.hsac.fitnesse.fixture.util.mobile.AppiumDriverManager;
import nl.hsac.fitnesse.fixture.util.selenium.driverfactory.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Fixture to connect FitNesse to appium.
 */
public class AppiumDriverSetup extends SeleniumDriverSetup {
    private static final String APP_CAPABILITY_NAME = "app";

    static {
        // ensure our helpers are used for Appium WebDrivers
        DriverManager manager = Environment.getInstance().getSeleniumDriverManager();
        AppiumDriverManager appiumDriverManager = new AppiumDriverManager(manager);
        Environment.getInstance().setSeleniumDriverManager(appiumDriverManager);
    }

    public AppiumDriverSetup() {
        getSecretCapabilities().add("testobject_api_key");
    }

    public boolean connectToAndroidDriverAtWithCapabilities(String url, Map<String, Object> capabilities)
            throws MalformedURLException {
        return createAndSetRemoteWebDriver(AndroidDriver::new, url, new DesiredCapabilities(capabilities));
    }

    public boolean connectToIosDriverAtWithCapabilities(String url, Map<String, Object> capabilities)
            throws MalformedURLException {
        return createAndSetRemoteWebDriver(IOSDriver::new, url, new DesiredCapabilities(capabilities));
    }

    public boolean connectToWindowsDriverAtWithCapabilities(String url, Map<String, Object> capabilities) throws MalformedURLException {
        return this.createAndSetRemoteWebDriver(WindowsDriver<WindowsElement>::new, url, new DesiredCapabilities(capabilities));
    }

    public boolean connectToWindowsDriverAtAndSelectWindow(String url, String window) throws MalformedURLException {
        Map<String, Object> capabilities = new HashMap<>();
        capabilities.put("app", "Root");
        if(connectToWindowsDriverAtWithCapabilities(url, capabilities)){
            String windowHandle = getWindowHandleForName(window);

            stopDriver();
            capabilities.clear();

            capabilities.put("appTopLevelWindow", windowHandle);
            return connectToWindowsDriverAtWithCapabilities(url, capabilities);
        }
        return false;
    }

    private String getWindowHandleForName(String applicationTitle) {
        String handle = getHelper().driver().findElement(By.name(applicationTitle)).getAttribute("NativeWindowHandle");

        String hex = String.format("0x%s", Integer.toHexString(Integer.parseInt(handle)));
        return hex;
    }

    @Override
    protected boolean createAndSetRemoteWebDriver(BiFunction<URL, Capabilities, ? extends RemoteWebDriver> constr,
                                                  String url,
                                                  DesiredCapabilities desiredCapabilities)
            throws MalformedURLException {
        Object appValue = desiredCapabilities.getCapability(APP_CAPABILITY_NAME);
        if (appValue instanceof String) {
            String appLocation = (String) appValue;
            String fullPath = getFilePathFromWikiUrl(appLocation);
            desiredCapabilities.setCapability(APP_CAPABILITY_NAME, fullPath);
        }
        return super.createAndSetRemoteWebDriver(constr, url, desiredCapabilities);
    }
}
