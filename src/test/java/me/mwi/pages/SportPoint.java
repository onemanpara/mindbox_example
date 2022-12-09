package me.mwi.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class SportPoint {

    private final static SelenideElement
            phoneNumberInput = $("[name=auth_phone]"),
            authCodeInput = $("#authorizeCode"),
            userName = $("[name=NAME]"),
            userLastName = $("[name=LAST_NAME]");

    public SportPoint setPhoneNumber(String phoneNumber) {
        phoneNumberInput.setValue(phoneNumber).pressEnter();
        return this;
    }

    public SportPoint setAuthCode(String authCode) {
        authCodeInput.setValue(authCode).pressEnter();
        return this;
    }

    public SportPoint getUserFIO(String firstName, String lastName) {
        userName.shouldHave(attribute("value", firstName));
        userLastName.shouldHave(attribute("value", lastName));
        return this;
    }

    public SportPoint checkAuth() {
        $(byText("Вы зарегистрированы и успешно авторизовались.")).shouldBe(visible);
        return this;
    }

}
