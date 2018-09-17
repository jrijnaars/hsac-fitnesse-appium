package nl.hsac.fitnesse.fixture.util.mobile.by;

import io.appium.java_client.MobileBy;
import io.appium.java_client.windows.WindowsElement;
import nl.hsac.fitnesse.fixture.util.selenium.by.LazyPatternBy;
import org.openqa.selenium.By;

public class WindowsBy {
    private static final String CONTAINS_EXACT = "Name=='%1$s' OR value=='%1$s'";

    private static final String CONTAINS_PARTIAL = "Name CONTAINS '%1$s' OR value CONTAINS '%1$s'";

    public static By exactText(String text) {
        return new WindowsBy.Predicate(CONTAINS_EXACT, text);
    }

    public static By partialText(String text) {
        return new WindowsBy.Predicate(CONTAINS_PARTIAL, text);
    }

    public static AppiumHeuristicBy<WindowsElement> heuristic(String text) {
        return new AppiumHeuristicBy<>(MobileBy.AccessibilityId(text), exactText(text), partialText(text));
    }

    public static AppiumHeuristicBy<WindowsElement> buttonHeuristic(String text) {
        return new AppiumHeuristicBy<>(MobileBy.AccessibilityId(text), exactText(text), partialText(text));
    }

    public static class ClassChain extends LazyPatternBy {
        /**
         * Creates By based on pattern, supporting placeholder replacement.
         * Pattern will only be filled in when By is evaluated.
         *
         * @param pattern    basic pattern, possibly with placeholders {@link String#format}.
         * @param parameters values for placeholders.
         */
        public ClassChain(String pattern, String... parameters) {
            super(pattern, parameters);
        }

        @Override
        protected By createNested(String expr) {
            return MobileBy.windowsAutomation(expr);
        }
    }

    public static class Predicate extends LazyPatternBy {
        /**
         * Creates By based on pattern, supporting placeholder replacement.
         * Pattern will only be filled in when By is evaluated.
         *
         * @param pattern    basic pattern, possibly with placeholders {@link String#format}.
         * @param parameters values for placeholders.
         */
        public Predicate(String pattern, String... parameters) {
            super(pattern, parameters);
        }

        @Override
        protected By createNested(String expr) {
            return MobileBy.windowsAutomation(expr);
        }
    }
}
