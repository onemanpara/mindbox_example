package me.mwi.pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static me.mwi.tests.TestData.testUserLastName;
import static me.mwi.tests.TestData.testUserName;

public class MindBox {

    private final SelenideElement
            actionButton = $(byText("Действия")),
            userName = $("#UserName"),
            userPassword = $("#Password"),
            cellWithAuthCode = $(byText(testUserLastName + " " + testUserName));

    public MindBox setUserName(String mindBoxUserName) {
        userName.setValue(mindBoxUserName);
        return this;
    }

    public void setUserPassword(String mindBoxUserPassword) {
        userPassword.setValue(mindBoxUserPassword).pressEnter();
    }

    public String getAuthCode() {
        actionButton.shouldBe(visible, Duration.ofSeconds(30)).click();
        cellWithAuthCode.parent()
                .parent()
                .parent()
                .parent()
                .preceding(0)
                .click();
        String sourceAuthCode = $x("//div[@type='paragraph'][contains(text(), 'Ваш код подтверждения')]").innerText();
        String authCode = String.valueOf(sourceAuthCode.charAt(23)) +
                String.valueOf(sourceAuthCode.charAt(24)) +
                String.valueOf(sourceAuthCode.charAt(25)) +
                String.valueOf(sourceAuthCode.charAt(26));
        return authCode;
    }

}
