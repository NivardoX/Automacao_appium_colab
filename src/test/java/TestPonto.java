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
import static junit.framework.TestCase.assertEquals;

;

public class TestPonto {
    private AppiumDriver driver;


    private void trocar_empresa(){  MobileElement dropDown = (MobileElement) driver.findElement
            (By.id("br.com.fortes.appcolaborador:id/iv_company"));

        dropDown.click();

        List<MobileElement> empresas = driver.findElements
                (By.id("br.com.fortes.appcolaborador:id/constraint_layout_profile"));
        empresas.get(2).click();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
    public void test_matricula_folha() {

        logar();

        MobileElement empresa = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresa.click();

        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement ponto_button = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/pointt"));

        ponto_button.click();

        List<MobileElement> rc_matriculas = driver.findElements
                (By.id("br.com.fortes.appcolaborador:id/constraint_layout_profile"));

        assert (rc_matriculas.size() > 1);

    }

    @Test
    public void ponto_mes_ano() {


        logar();

        MobileElement empresa = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresa.click();

        try {
            sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement ponto_button = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/pointt"));

        ponto_button.click();

        MobileElement rc_matricula = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/constraint_layout_profile"));

        rc_matricula.click();

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement scroll_view = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/point_month_recyclerview"));

        List<MobileElement> anos = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_item_header_name"));

        List<MobileElement> meses = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_month"));

        boolean flag = true;
        if (meses.size() == 0) {
            if (anos.size() == 0) {
                flag = true;
            } else {
                flag = false;
            }
        }
        assert (flag);

    }

    @Test
    public void test_batidas_refresh() {

        logar();

        MobileElement empresa = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresa.click();

        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement ponto_button = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/pointt"));

        ponto_button.click();

        List<MobileElement> rc_matriculas = driver.findElements
                (By.id("br.com.fortes.appcolaborador:id/constraint_layout_profile"));

        rc_matriculas.get(0).click();

        try {
            sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

        MobileElement toolbar = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/toolbar"));
        MobileElement title = (MobileElement) toolbar.findElement(By.className("android.widget.TextView"));

        assertEquals("Histórico de Batidas",title.getText());




    }

    @Test
    public void att_dados_troca_empresa(){

        logar_cpf("01530880521");

        MobileElement empresa = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresa.click();

        try {
            sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement ponto_button = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/pointt"));

        ponto_button.click();

        List<MobileElement> rc_matriculas1 = driver.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_mat"));

        trocar_empresa();


        List<MobileElement> rc_matriculas2 = driver.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_mat"));

        for (int i = 0; i < rc_matriculas2.size() ; i++){
            System.out.println(rc_matriculas2.get(i).getText());
        }


        boolean flagTodasIguas = true;

        if(rc_matriculas1.size() != rc_matriculas2.size()){
            assert (true);
        }else if(rc_matriculas1.size()== 0 && rc_matriculas2.size() == 0){
            assert(true);
        }else{
            for (int i = 0; i < rc_matriculas2.size() ; i++){
                String aux1 = rc_matriculas2.get(i).getText();
                for(int j = 0; j < rc_matriculas1.size() ; j++){
                    String aux2 = (rc_matriculas1.get(i).getText());
                        if(!aux1.equals(aux2)){
                            flagTodasIguas = false;
                        }
                }
            }

            assert(flagTodasIguas);

        }


    }

}
