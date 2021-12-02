package ru.inventorium.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.inventorium.qa.config.CredentialsConfig;
import ru.inventorium.qa.pages.RegistrationsPage;
import ru.inventorium.qa.helpers.Attach;

import static java.lang.String.format;

public class TestBase {
    RegistrationsPage registrationsPage = new RegistrationsPage();
    public static CredentialsConfig getCredentials =
            ConfigFactory.create(CredentialsConfig.class);
    @BeforeAll
    static void beforeAll() {
        String url = System.getProperty("url", null);
        String login = getCredentials.login();
        String password = getCredentials.password();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.startMaximized = true;
        String remoteConfig = format("https://%s:%s@%s", login, password, url);
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";
        System.out.println(remoteConfig);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
