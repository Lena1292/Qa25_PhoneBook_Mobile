package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;

public class LoginSecondTests extends AppiumConfig {


    @Test
    public void loginSuccess(){
        new AuthenticationScreen(driver)
                .fillEmail("elena.krylataya@gmail.com")
                .fillPassword("Lenakira11$")
                .submitLogin()
                .isAccountOpened()
                .logout();
    }


    @Test
    public void loginSuccessModel(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("elena.krylataya@gmail.com").password("Lenakira11$").build())
                .submitLogin()
                .isAccountOpened()
                .logout();
    }

    @Test
    public void loginWrongEmail(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("elena.krylatayagmail.com").password("Lenakira11$").build())
                .submitLoginNegative()
                .isErrorMessageContainsText("Login or Password incorrect");
    }


}
