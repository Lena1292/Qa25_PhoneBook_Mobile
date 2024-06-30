package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class ContactListScreen extends BaseScreen {
    public ContactListScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    AndroidElement activityTextView;

    @FindBy(xpath = "//*[@content-desc='More options']")
    AndroidElement menuOption;

    @FindBy(xpath = "//*[@text='Logout']")
    AndroidElement logoutBtn;

    @FindBy(xpath = "//*[@content-desc='add']")
    AndroidElement plusBtn;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName]")
    List<AndroidElement> contactNameList;


    public boolean isActivityTitleDisplayed(String text) {

        //return activityTextView.getText().contains("Contact list");
        return isShouldHave(activityTextView, text, 10);
    }


    public AuthenticationScreen logout() {
        if (activityTextView.getText().equals("Contact list")) {
            menuOption.click();
            logoutBtn.click();
        }
        return new AuthenticationScreen(driver);
    }

    public ContactListScreen isAccountOpened() {
        Assert.assertTrue(isActivityTitleDisplayed("Contact list"));
        return this;
    }

    public AddNewContactScreen openContactForm() {
        plusBtn.click();
        return new AddNewContactScreen(driver);
    }

    public ContactListScreen isContactAddedByName(String name, String lastName) {
        isShouldHave(activityTextView, "Contact list", 5);
        boolean isPresent = false;

        for (AndroidElement el : contactNameList) {
            if (el.getText().equals(name + " " + lastName)) {
                isPresent = true;
                break;
            }
        }
        Assert.assertTrue(isPresent);
        return this;
    }

}
