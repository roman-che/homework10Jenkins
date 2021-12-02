package ru.inventorium.qa.tests;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Locale;
@Tag("Maintest")
public class StudentFormTestWithPageObjectsTestsAndFaker extends TestBase {

    Faker faker = new Faker(new Locale("en"));

    FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("ru"), new RandomService());

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            phone = fakeValuesService.regexify("[1-9]{10}"),//генерируем случайное число из 10 цифр
            gender = "Male",
            subject = "English",
            pictureFile = "lena.jpg",
            hobby = "Sports",
            address = faker.address().fullAddress(),
            city = "Delhi",
            state = "NCR";

    @Test
    void studentFormTest() {

        registrationsPage.openPage();

        registrationsPage.typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(email)
                .setGender(gender)
                .typePhone(phone)
                .typeBirthDay("23","April","1977")
                .setSubject(subject)
                .setHobby(hobby)
                .uploadPicture("src/test/resources/", pictureFile)
                .typeAddress(address)
                .typeState(state)
                .typeCity(city);

        registrationsPage.submitForm();

        registrationsPage.checkResultsValue("Student Name", firstName + " " + lastName)
                .checkResultsValue("Student Email", email)
                .checkResultsValue("Mobile", phone)
                .checkResultsValue("Subjects", subject)
                .checkResultsValue("Picture", pictureFile)
                .checkResultsValue("Address", address)
                .checkResultsValue("State and City", state + " " + city);

    }

}
