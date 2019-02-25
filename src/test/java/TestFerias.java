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
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertFalse;

;

public class TestFerias {
    private AppiumDriver driver;

    private void logar() {
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

    private void logar_cpf(String cpf_str) {
        MobileElement cpf = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/et_cpf"));
        MobileElement pass = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/et_password"));
        MobileElement login_button = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/cpf_sign_in_button"));


        cpf.sendKeys(cpf_str);
        pass.sendKeys("12345678");
        login_button.click();
        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void deslogar() {
        MobileElement empresa = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresa.click();

        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement scroll_view = (MobileElement) driver.findElement((By.id("br.com.fortes.appcolaborador:id/scroll_view_profile")));

        MobileElement logout_btn = (MobileElement) scroll_view
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
        File app = new File("/home/nivardo/nivardo/lia/automacao/", "app-homolog_ifce.apk");
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


    @After
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void test_vizualizar_ferias(){
        logar();

        MobileElement empresa = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresa.click();

        try {
            sleep(6000) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement folha =(MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/financial"));
        folha.click();

        MobileElement ferias = (MobileElement) driver.findElement(MobileBy
               .AndroidUIAutomator("new UiSelector().description(\"FÉRIAS\");"));

        ferias.click();

        MobileElement scroll_view = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/viewpager"));

        List<MobileElement> meses = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/item"));

        assert (meses.get(0).isDisplayed() && meses.get(0).isEnabled());

    }

    @Test
    public void test_vizualizar_matriculas(){
        logar();

        MobileElement empresa = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresa.click();

        try {
            sleep(6000) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement folha =(MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/financial"));
        folha.click();

        MobileElement ferias = (MobileElement) driver.findElement(MobileBy
                .AndroidUIAutomator("new UiSelector().description(\"FÉRIAS\");"));

        ferias.click();


        MobileElement scroll_view = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/viewpager"));

        List<MobileElement> meses = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/item"));

        meses.get(0).click();

        List<MobileElement> matricula =driver.findElements(By.id
                ("br.com.fortes.appcolaborador:id/text_item_header_name"));

        System.out.println(matricula.size());
        assert(matricula.size() != 0);




    }
    @Test
    public void test_vizualizar_ferias_meses(){
        logar();

        MobileElement empresa = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresa.click();

        try {
            sleep(6000) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement folha =(MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/financial"));
        folha.click();

        MobileElement ferias = (MobileElement) driver.findElement(MobileBy
                .AndroidUIAutomator("new UiSelector().description(\"FÉRIAS\");"));

        ferias.click();

        MobileElement scroll_view = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/viewpager"));

        List<MobileElement> meses = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/item"));

        assert (meses.size() >= 1);

    }

    @Test
    public void test_refresh(){

        logar();

        MobileElement empresa = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresa.click();

        try {
            sleep(6000) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement folha =(MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/financial"));
        folha.click();

        MobileElement ferias = (MobileElement) driver.findElement(MobileBy
                .AndroidUIAutomator("new UiSelector().description(\"FÉRIAS\");"));

        ferias.click();




        Dimension size = driver.manage().window().getSize();
        System.out.println(size);
        int startX = 0;
        int endX = 0;
        int startY = 0;
        int endY = 0;


        startY = (int) (size.height * 0.70);
        endY = (int) (size.height * 0.30);
        startX = (size.width / 2);
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



        ferias = (MobileElement) driver.findElement(MobileBy
                .AndroidUIAutomator("new UiSelector().description(\"FÉRIAS\");"));

        assert(ferias.isDisplayed());

    }


}
