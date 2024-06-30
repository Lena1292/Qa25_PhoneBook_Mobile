package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class AddNewContactTests extends AppiumConfig {

    @BeforeClass
    public void preCondition() {
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("elena.krylataya@gmail.com").password("Lenakira11$").build())
                .submitLogin();

    }

    @Test
    public void createNewContactSuccess() {
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Simon")
                .lastName("Wow"+i)
                .email("wow"+i+"@gmail.com")
                .phone("1234567"+i)
                .address("NY")
                .description("Friend")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(),contact.getLastName());

    }

    @Test
    public void createNewContactSuccessReq() {

    }


    @AfterClass
    public void postCondition() {
        new ContactListScreen(driver)
                .logout();

    }
}
