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
import static junit.framework.TestCase.fail;

public class TestContraCheque {
    private AppiumDriver driver;

    private void trocar_empresa() {
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

    private Boolean isSorted(List<MobileElement> a) {
        int i = 0;
        if (a.size() == 1) {
            return true;
        }
        for (i = 0; i < a.size(); i++) {
            return Integer.parseInt(a.get(i).getText()) < Integer.parseInt(a.get(i + 1).getText());
        }
        return true;
    }

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
    public void test_anos_ordenados() {

        logar();

        MobileElement empresa = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresa.click();

        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement folha = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/financial"));
        folha.click();


        MobileElement scroll_view = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/frame_layout_principal"));

        List<MobileElement> anos = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_item_header_name"));

        assert (isSorted(anos));
    }

    @Test
    public void test_exibir_download_pdf() {

        logar_cpf("01530880521");

        MobileElement empresa = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresa.click();

        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement folha = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/financial"));
        folha.click();


        MobileElement scroll_view = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/listShowPayChecks"));

        List<MobileElement> meses = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_item_paycheck_name"));

        meses.get(0).click();

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement view = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/paycheck_registry_recyclerview"));

        List<MobileElement> matriculas = driver.findElements(By.id
                ("br.com.fortes.appcolaborador:id/item"));

        matriculas.get(0).click();

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement download_pdf = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/action_delete"));

        assert (download_pdf.isDisplayed() && download_pdf.isEnabled());
    }

    @Test
    public void att_dados_troca_empresa() {

        logar_cpf("01530880521");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MobileElement empresa = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresa.click();

        try {
            sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement folha = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/financial"));
        folha.click();

        List<MobileElement> rc_matriculas1 = driver.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_item_paycheck_name"));
        for (int i = 0; i < rc_matriculas1.size(); i++) {
            System.out.println(rc_matriculas1.get(i).getText());
        }

        trocar_empresa();


        List<MobileElement> rc_matriculas2 = driver.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_item_paycheck_name"));

        for (int i = 0; i < rc_matriculas2.size(); i++) {
            System.out.println(rc_matriculas2.get(i).getText());
        }


        boolean flagTodasIguas = true;

        if (rc_matriculas1.size() != rc_matriculas2.size()) {
            assert (true);
        } else if (rc_matriculas1.size() == 0 && rc_matriculas2.size() == 0) {
            assert (true);
        } else {
            for (int i = 0; i < rc_matriculas2.size(); i++) {
                String aux1 = rc_matriculas2.get(i).getText();
                for (int j = 0; j < rc_matriculas1.size(); j++) {
                    String aux2 = (rc_matriculas1.get(j).getText());
                    if (!aux1.equals(aux2)) {
                        flagTodasIguas = false;
                    }
                }
            }

            assert (flagTodasIguas);

        }
    }

    @Test
    public void test_exibir_matriculas() {

        logar_cpf("01530880521");

        List<MobileElement> empresas = driver.findElements(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresas.get(0).click();

        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement folha = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/financial"));
        folha.click();


        MobileElement scroll_view = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/listShowPayChecks"));

        List<MobileElement> meses = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_item_paycheck_name"));

        meses.get(0).click();

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement view = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/paycheck_registry_recyclerview"));

        List<MobileElement> matriculas = driver.findElements(By.id
                ("br.com.fortes.appcolaborador:id/text_item_header_name"));

        for (int i = 0; i < matriculas.size(); i++) {
            if (!matriculas.get(i).getText().contains("Matrícula")) {
                fail("Não contém Matrŕicula na string.");
            }
        }

        assert true;


    }

    @Test
    public void test_exibir_folhas() {

        logar_cpf("01530880521");

        MobileElement empresa = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresa.click();

        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement folha = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/financial"));
        folha.click();


        MobileElement scroll_view = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/listShowPayChecks"));

        List<MobileElement> meses = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_item_paycheck_name"));

        meses.get(0).click();

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement view = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/paycheck_registry_recyclerview"));

        List<MobileElement> matriculas = driver.findElements(By.id
                ("br.com.fortes.appcolaborador:id/text_item_paycheck_name"));

        for (int i = 0; i < matriculas.size(); i++) {
            if (matriculas.get(i).getText().contains("Indefinido")) {
                fail("Exibindo Indefinido");
            }
        }

        assert true;


    }

    @Test
    public void test_atualizar() {

        logar();

        MobileElement empresa = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresa.click();

        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement folha = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/financial"));
        folha.click();


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

        folha = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/financial"));
        assert (folha.isDisplayed());
    }

    @Test
    public void folha_empty_state() {

        logar_cpf("01530880521");

        List<MobileElement> empresas = driver.findElements(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresas.get(4).click();

        try {
            sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement folha = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/financial"));
        folha.click();

        try {
            MobileElement nothing_point = (MobileElement) driver.findElement(
                    By.id("br.com.fortes.appcolaborador:id/nothing_paycheck"));
        } catch (Exception e) {
            assert (false);
        }

        assert true;


    }

}
