import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

public class testBase {
    AppiumDriver driver;

    void trocar_empresa() {
        MobileElement dropDown = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/iv_company"));

        dropDown.click();
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<MobileElement> empresas = driver.findElements
                (By.id("br.com.fortes.appcolaborador:id/constraint_layout_profile"));
        empresas.get(2).click();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void logar() {
        MobileElement cpf = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/et_cpf"));
        MobileElement pass = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/et_password"));
        MobileElement login_button = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/cpf_sign_in_button"));


        cpf.sendKeys("00717420345");
        pass.sendKeys("12345678");
        login_button.click();
        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void logar_cpf_pass(String cpf_str, String pass_str) {
        MobileElement cpf = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/et_cpf"));
        MobileElement pass = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/et_password"));
        MobileElement login_button = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/cpf_sign_in_button"));


        cpf.sendKeys(cpf_str);
        pass.sendKeys(pass_str);
        login_button.click();
        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    Boolean isSorted(List<MobileElement> a) {
        int i = 0;
        if (a.size() == 1) {
            return true;
        }
        for (i = 0; i < a.size(); i++) {
            return Integer.parseInt(a.get(i).getText()) < Integer.parseInt(a.get(i + 1).getText());
        }
        return true;
    }

    void logar_cpf(String cpf_str) {
        MobileElement cpf = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/et_cpf"));
        MobileElement pass = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/et_password"));
        MobileElement login_button = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/cpf_sign_in_button"));


        cpf.sendKeys(cpf_str);
        pass.sendKeys("12345678");
        login_button.click();
        try {
            sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void deslogar() {
        MobileElement empresa = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresa.click();

        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement scroll_view = (MobileElement) driver.findElement((By.id("br.com.fortes.appcolaborador:id/scroll_view_profile")));

        MobileElement logout_btn = scroll_view
                .findElement(MobileBy
                        .AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                                + "new UiSelector().text(\"Sair\"));"));

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logout_btn = (MobileElement) driver.findElement((By.id("br.com.fortes.appcolaborador:id/btn_logout")));

        logout_btn.click();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Before
    public void setup() throws MalformedURLException {
        String path = "/home/nivardo/Documents/Automacao_appium_colab/";

        File app = new File(path, "app-homolog_ifce.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device", "Android");

        //mandatory capabilities
        capabilities.setCapability("deviceName", "Android");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("autoGrantPermissions", "true");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        //other caps
        capabilities.setCapability("app", app.getAbsolutePath());
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    void atualizar(){

        Dimension size = driver.manage().window().getSize();
        System.out.println(size);
        int endX = 0;


        int  startY = (int) (size.height * 0.70);
        int endY = (int) (size.height * 0.30);
        int startX = (size.width / 2);
        //Swipe from Bottom to Top.

        new TouchAction(driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(200)))
                .moveTo(PointOption.point(endX, endY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .release()
                .perform();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
