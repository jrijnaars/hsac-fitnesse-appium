package nl.hsac.fitnesse.fixture.util.mobile;

import io.appium.java_client.MobileBy;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import nl.hsac.fitnesse.fixture.util.mobile.by.AppiumHeuristicBy;
import nl.hsac.fitnesse.fixture.util.mobile.by.IsDisplayedFilter;
import nl.hsac.fitnesse.fixture.util.mobile.by.WindowsBy;
import nl.hsac.fitnesse.fixture.util.mobile.scroll.ScrollHelper;
import org.openqa.selenium.By;

import static nl.hsac.fitnesse.fixture.util.FirstNonNullHelper.firstNonNull;

public class WindowsHelper extends AppiumHelper<WindowsElement, WindowsDriver<WindowsElement>> {

    public WindowsHelper() {
        setScrollHelper(new ScrollHelper<>(this));
    }

    @Override
    public By placeToBy(String place) {
        return firstNonNull(place,
                super::placeToBy);
    }

    @Override
    protected By getElementBy(String place) {
        return WindowsBy.heuristic(place);
    }

    @Override
    protected By getClickBy(String place) {
        return new AppiumHeuristicBy<>(WindowsBy.buttonHeuristic(place), WindowsBy.heuristic(place));
    }

    @Override
    protected By getContainerBy(String container) {
        return WindowsBy.heuristic(container);
    }

    @Override
    protected By getElementToCheckVisibilityBy(String text) {
        return new AppiumHeuristicBy<>(new IsDisplayedFilter<WindowsElement>(), MobileBy.AccessibilityId(text), WindowsBy.partialText(text));
    }
}
