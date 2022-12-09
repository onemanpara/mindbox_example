package me.mwi.tests;

import me.mwi.pages.MindBox;
import me.mwi.pages.SportPoint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WindowType;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;
import static io.qameta.allure.Allure.step;
import static me.mwi.tests.TestData.*;

public class AuthTests extends TestBase{

    MindBox mindBox = new MindBox();
    SportPoint sportPoint = new SportPoint();


    @DisplayName("Авторизация пользователя")
    @Test
    void positiveAuthorizationTest() {
        step("Открываем страницу авторизации SP", () -> {
            open("https://dev.sportpoint.mwi.me/auth/");
        });
        step("Вводим номер телефона и отправляем код", () -> {
            sportPoint.setPhoneNumber(phoneNumber);
        });
        step("Открываем новую вкладку в браузере с Mindbox", () -> {
            switchTo().newWindow(WindowType.TAB);
            open("https://sportpoint-stage.mindbox.ru/");
        });
        step("Авторизуемся в MindBox", () -> {
            mindBox
                    .setUserName(mindBoxUserName)
                    .setUserPassword(mindBoxPassword);
        });
        step("Забираем код авторизации из Mindbox и вводим его в форме авторизации на SP", () -> {
            String authCode = mindBox.getAuthCode();
            switchTo().window("Авторизация");
            sportPoint.setAuthCode(authCode);
        });
        step("Проверяем, что авторизация прошла", () -> {
            sportPoint.checkAuth();
        });
        step("Открываем страницу пользователя и проверяем имя и фамилию", () -> {
            open("https://dev.sportpoint.mwi.me/personal/profile/");
            sportPoint.getUserFIO(testUserName, testUserLastName);
        });
    }
}
