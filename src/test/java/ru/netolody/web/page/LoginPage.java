package ru.netolody.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netolody.web.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement loginI = $("[data-test-id=login] input");
    private final SelenideElement passwordI = $("[data-test-id=password] input");
    private final SelenideElement loginButton = $("[data-test-id=action-login]");
    private final SelenideElement errorNotif = $("[data-test-id='error-notification'] .notification__content");

    public void verifyErrorNotif(String expectedText) {
        errorNotif.shouldHave(exactText(expectedText)).shouldBe(visible);
    }

    public VerificationPage validLogin(DataHelper.LoginData info) {
        login(info);
        return new VerificationPage();
    }

    public void login(DataHelper.LoginData info) {
        loginI.setValue(info.getLogin());
        passwordI.setValue(info.getPassword());
        loginButton.click();
    }
}

