package page;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class OnboardScreen {
    private final SelenideElement continueButton =
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button"));
    private final SelenideElement primaryTextView = $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"));
    private final SelenideElement switchView = $(AppiumBy.id("org.wikipedia.alpha:id/switchView"));
    private final SelenideElement getStartedButton = $(AppiumBy.id("fragment_onboarding_done_button"));

    @Step("Далее")
    public void nextScreen(){
        continueButton.click();
    }

    @Step("Завершение онбординга")
    public void finishOnboarding(){
        getStartedButton.click();
    }

    @Step("Проверка заголовка")
    public void checkTitle(String title){
        primaryTextView.shouldHave(text(title));
    }

    @Step("Переключение свитчера")
    public void switchOff(){
        switchView.click();
    }
}
