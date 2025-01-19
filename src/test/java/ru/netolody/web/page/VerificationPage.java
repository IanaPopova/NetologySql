package ru.netolody.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


public class VerificationPage {
    private final SelenideElement codeI = $("[data-test-id=code] input");
    private final SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private final SelenideElement errorNotif = $("[data-test-id='error-notification'] .notification__content");

    public VerificationPage() {
        codeI.shouldBe(Condition.visible);
    }

    public void verifyErrorNotif(String expectedText) {
        errorNotif.shouldHave(exactText(expectedText)).shouldBe(visible);
    }

    public DashboardPage validVerify(String verificationCode) {
        verify(verificationCode);
        return new DashboardPage();
    }

    public void verify(String verificationCode) {
        codeI.setValue(verificationCode);
        verifyButton.click();;
    }
}
