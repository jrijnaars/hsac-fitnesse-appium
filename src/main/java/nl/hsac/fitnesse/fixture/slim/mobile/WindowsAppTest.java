package nl.hsac.fitnesse.fixture.slim.mobile;

import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import nl.hsac.fitnesse.fixture.util.mobile.WindowsHelper;

import java.util.ArrayList;
import java.util.List;

public class WindowsAppTest extends MobileTest<WindowsElement, WindowsDriver<WindowsElement>> {
    public WindowsAppTest() {
        super();
    }

    public WindowsAppTest(int secondsBeforeTimeout) {
        super(secondsBeforeTimeout);
    }

    @Override
    protected WindowsHelper getMobileHelper() {
        return (WindowsHelper) super.getMobileHelper();
    }

    public List<String> windowsHandles() {
        return new ArrayList<>(getSeleniumHelper().driver().getWindowHandles());
    }

}
