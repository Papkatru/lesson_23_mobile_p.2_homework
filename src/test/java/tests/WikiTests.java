package tests;

import base.TestBase;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class WikiTests extends TestBase {

    @Test
    void searchTest() {
        step("Close onboarding screen", Selenide::back);
        step("Type search", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Verify content found", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(CollectionCondition.sizeGreaterThan(0)));
    }

    @Test
    void searchWithByTextLocatorTest() {
        step("Close onboarding screen", () -> back());
        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Verify content found", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(CollectionCondition.sizeGreaterThan(0)));
    }

    @Test
    void onboardingScreenTest(){
        onboardScreen.checkTitle("The Free Encyclopedia …in over 300 languages");
        onboardScreen.nextScreen();
        onboardScreen.checkTitle("New ways to explore");
        onboardScreen.nextScreen();
        onboardScreen.checkTitle("Reading lists with sync");
        onboardScreen.nextScreen();
        onboardScreen.switchOff();
        onboardScreen.checkTitle("Send anonymous data");
        onboardScreen.finishOnboarding();
    }
}
