package ru.netolody.web.test;

import lombok.var;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netolody.web.data.DataHelper;
import ru.netolody.web.data.SQLHelper;
import ru.netolody.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netolody.web.data.SQLHelper.cleanAuthCodes;
import static ru.netolody.web.data.SQLHelper.cleanDatabase;

public class LoginTest {
    LoginPage loginPage;
    DataHelper.LoginData loginData = DataHelper.getLoginData();

    @AfterAll
    static void tearDownAll() {
        cleanDatabase();
    }

    @AfterEach
    void tearDown() {
        cleanAuthCodes();
    }

    @BeforeEach
    void setUp() {
        loginPage = open("http://localhost:9999", LoginPage.class);
    }

    @Test
    void shouldLoginSuccessfully() {
        var verificationPage = loginPage.validLogin(loginData);
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldNotLoginWithInvalidData() {
        var loginData = DataHelper.generateUser();
        loginPage.login(loginData);
        loginPage.verifyErrorNotif("Ошибка! Неверно указан логин или пароль");
    }

    @Test
    void shouldNotLoginWithInvalidVerifyCode() {
        var verificationPage = loginPage.validLogin(loginData);
        var verificationCode = DataHelper.generateVerCode();
        verificationPage.verify(verificationCode.getCode());
        verificationPage.verifyErrorNotif("Ошибка! Неверно указан код! Попробуйте еще раз.");
    }

}
