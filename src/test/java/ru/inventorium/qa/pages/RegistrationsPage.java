package ru.inventorium.qa.pages;

import com.codeborne.selenide.SelenideElement;
import ru.inventorium.qa.pages.components.CalendarComponent;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationsPage {
    // locators & elements
    private final String FORM_TITLE = "Student Registration Form";
    SelenideElement
            formTitle = $(".practice-form-wrapper"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            resultsTable = $(".table-responsive"),
            emailInput = $("#userEmail"),
            phoneInput = $("#userNumber"),
            subjectsInput = $("#subjectsInput"),
            hobbyInput = $("#hobbiesWrapper"),
            pictureInput = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            cityInput = $("#react-select-4-input"),
            stateInput = $("#react-select-3-input"),
            submitButton = $("#submit");

    public CalendarComponent calendar = new CalendarComponent();
    //actions

    public RegistrationsPage openPage() {
        open("https://demoqa.com/automation-practice-form");
        formTitle.shouldHave(text(FORM_TITLE));
        return this;
    }

    public RegistrationsPage typeFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationsPage typeLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationsPage typeEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    public RegistrationsPage setGender(String value) {
        $("[name=gender][value=" + value + "]").parent().click();
        return this;
    }

    public RegistrationsPage typePhone(String value) {
        phoneInput.setValue(value);
        return this;
    }
    public RegistrationsPage typeBirthDay(String day,String month, String year) {
        this.calendar.setDate(day, month, year);
        return this;
    }

    public RegistrationsPage setSubject(String value) {
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationsPage setHobby(String value) {
        hobbyInput.$(byText(value)).click();
        return this;
    }

    public RegistrationsPage uploadPicture(String path, String file) {
        pictureInput.uploadFile(new File(path + file));
        pictureInput.uploadFromClasspath(file);
        return this;
    }

    public RegistrationsPage typeAddress(String value) {
        addressInput.setValue(value);
        return this;
    }

    public RegistrationsPage typeCity(String value) {
        cityInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationsPage typeState(String value) {
        stateInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationsPage submitForm() {
        submitButton.scrollTo();
        submitButton.click();
        return this;
    }

    public RegistrationsPage checkResultsValue(String key, String value) {
        resultsTable.$(byText(key))
                .parent().shouldHave(text(value));
        return this;
    }

}