package base;

import com.codeborne.selenide.Configuration;
import drivers.BrowserstackMobileDriver;
import drivers.LocalMobileDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import page.OnboardScreen;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class TestBase {

    public OnboardScreen onboardScreen = new OnboardScreen();

    @BeforeAll
    public static void setup() throws Exception {
        String host = System.getProperty("env");
        switch (host) {
            case "remote":
                Configuration.browser = BrowserstackMobileDriver.class.getName();
                Configuration.browserSize = null;
                break;
            case "local":
                Configuration.browser = LocalMobileDriver.class.getName();
                Configuration.browserSize = null;
                break;
            default:
                throw new Exception("Wrong env");
        }
    }

    @BeforeEach
    public final void startDriver() {
        addListener("AllureSelenide", new AllureSelenide());

        open();
    }

    @AfterEach
    public final void afterEach() {
        if (System.getProperty("env").equals("remote")) {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            String sessionId = sessionId().toString();
            closeWebDriver();
            Attach.video(sessionId);
        } else {
            closeWebDriver();
        }
    }
}
