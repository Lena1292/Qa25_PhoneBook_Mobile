package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class BaseScreen {
    AppiumDriver<AndroidElement> driver;

    public BaseScreen(AppiumDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void type(AndroidElement element, String text) {
        element.click();
        element.clear();
        if (text != null) {
            element.sendKeys(text);
        }
        driver.hideKeyboard();
    }

    public boolean isShouldHave(AndroidElement element, String text, int time) {
        return new WebDriverWait(driver, time).until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void should(AndroidElement element, int time) {
        new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(element));
    }

    public void checkAlertText(String text) {
        Alert alert = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains(text));
        alert.accept();
    }

    public boolean isElementDisplayed(AndroidElement element) {
        try {
            should(element, 5);
            return element.isDisplayed();
        } catch (IllegalAccessError e) {
            return false;
        }
    }


//    public  boolean isElementPresentInList(By locator){
//        return driver.findElements(locator).size()>0;
//    }

    public  boolean isElementPresentInList(List<AndroidElement> list){
        return list.size()>0;
    }

}