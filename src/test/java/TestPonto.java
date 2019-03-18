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
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;
import static junit.framework.TestCase.*;

public class TestPonto {
    private AppiumDriver driver;


    private void trocar_empresa() {
        MobileElement dropDown = (MobileElement) driver.findElement
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
            sleep(8000);
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
    public void test_matricula_ponto() {

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
            flag = anos.size() == 0;
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
        MobileElement title = toolbar.findElement(By.className("android.widget.TextView"));

        assertEquals("Histórico de Batidas", title.getText());


    }

    @Test
    public void att_dados_troca_empresa() {

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
                    String aux2 = (rc_matriculas1.get(i).getText());
                    if (!aux1.equals(aux2)) {
                        flagTodasIguas = false;
                    }
                }
            }

            assert (flagTodasIguas);

        }


    }

    @Test
    public void ponto_empty_state() {

        logar_cpf("01530880521");

        List<MobileElement> empresas = driver.findElements(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresas.get(4).click();

        try {
            sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement ponto_button = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/pointt"));

        ponto_button.click();

        try {

            MobileElement nothing_point = (MobileElement) driver.findElement(
                    By.id("br.com.fortes.appcolaborador:id/nothing_point"));
        } catch (Exception e) {
            assert (false);
        }

        assert true;


    }

    @Test
    public void registrar_ponto() {

        List<String> meses = new ArrayList<>();
        meses.add("Janeiro");
        meses.add("Fevereiro");
        meses.add("Março");
        meses.add("Abril");
        meses.add("Maio");
        meses.add("Junho");
        meses.add("Julho");
        meses.add("Agosto");
        meses.add("Setembro");
        meses.add("Outubro");
        meses.add("Novembro");
        meses.add("Dezembro");

        logar_cpf("01607344521");

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

        MobileElement registrar_ponto_bttn = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/pm_beat_point"));

        registrar_ponto_bttn.click();

        try {
            sleep(22000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {

            MobileElement alert = (MobileElement) driver.findElement
                    (By.id("android:id/message"));

            if (alert.isDisplayed()) {
                assertFalse(alert.getText().equals
                        ("Não foi possível obter sua localização, tente novamente em alguns minutos."));

            }
        } catch (Exception e) {
            System.out.println("Localização detectada");
        }

        MobileElement hora = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/bp_tv_data_hour"));
        String hora_str = hora.getText();
        MobileElement date = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/bp_tv_data_date"));

        String data = date.getText();
        data = data.substring(3, 5);

        String mes = meses.get(Integer.parseInt(data));


        MobileElement registrar_bttn = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/btn_beat_point"));

        registrar_bttn.click();

        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<MobileElement> meses_tv = driver.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_month_single"));

        MobileElement ponto_mes = meses_tv.get(0);

        for (MobileElement m : meses_tv) {
            if (m.getText().equals(mes)) {
                ponto_mes = m;
                break;

            }

        }

        ponto_mes.click();

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        boolean flag_horas_iguais = false;
        List<MobileElement> horarios = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            String id = "br.com.fortes.appcolaborador:id/tv_point_" + i;
            System.out.println(id);
            horarios.add((MobileElement) driver.findElement
                    (By.id(id)));
            String hora_econtrada = horarios.get(i).getText();

            if (hora_econtrada.equals(hora_str)) {
                flag_horas_iguais = true;
                break;
            }
        }
        if (flag_horas_iguais) {
            assert true;
        } else {
            fail("O ponto batido não foi encontrado na tabela");
        }


    }

    @Test
    public void registrar_ponto_vazio() {

        List<String> meses = new ArrayList<>();
        meses.add("Janeiro");
        meses.add("Fevereiro");
        meses.add("Março");
        meses.add("Abril");
        meses.add("Maio");
        meses.add("Junho");
        meses.add("Julho");
        meses.add("Agosto");
        meses.add("Setembro");
        meses.add("Outubro");
        meses.add("Novembro");
        meses.add("Dezembro");

        logar_cpf("01530880521");

        List<MobileElement> empresas = driver.findElements(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresas.get(3).click();

        try {
            sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement ponto_button = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/pointt"));

        ponto_button.click();


        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MobileElement registrar_ponto_bttn = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/pm_beat_point"));

        registrar_ponto_bttn.click();

        try {
            sleep(22000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {

            MobileElement alert = (MobileElement) driver.findElement
                    (By.id("android:id/message"));

            if (alert.isDisplayed()) {
                assertFalse(alert.getText().equals
                        ("Não foi possível obter sua localização, tente novamente em alguns minutos."));

            }
        } catch (Exception e) {
            System.out.println("Localização detectada");
        }

        MobileElement hora = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/bp_tv_data_hour"));
        String hora_str = hora.getText();
        MobileElement date = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/bp_tv_data_date"));

        String data = date.getText();
        data = data.substring(3, 5);

        String mes = meses.get(Integer.parseInt(data));


        MobileElement registrar_bttn = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/btn_beat_point"));

        registrar_bttn.click();

        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<MobileElement> meses_tv = driver.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_month_single"));

        MobileElement ponto_mes = meses_tv.get(0);

        for (MobileElement m : meses_tv) {
            if (m.getText().equals(mes)) {
                ponto_mes = m;
                break;

            }

        }

        ponto_mes.click();

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        boolean flag_horas_iguais = false;
        List<MobileElement> horarios = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            String id = "br.com.fortes.appcolaborador:id/tv_point_" + i;
            System.out.println(id);
            horarios.add((MobileElement) driver.findElement
                    (By.id(id)));
            String hora_econtrada = horarios.get(i).getText();

            if (hora_econtrada.equals(hora_str)) {
                flag_horas_iguais = true;
                break;
            }
        }
        if (flag_horas_iguais) {
            assert true;
        } else {
            fail("O ponto batido não foi encontrado na tabela");
        }


    }

    @Test
    public void registrar_ponto_matriculas() {

        List<String> meses = new ArrayList<>();
        meses.add("Janeiro");
        meses.add("Fevereiro");
        meses.add("Março");
        meses.add("Abril");
        meses.add("Maio");
        meses.add("Junho");
        meses.add("Julho");
        meses.add("Agosto");
        meses.add("Setembro");
        meses.add("Outubro");
        meses.add("Novembro");
        meses.add("Dezembro");

        logar_cpf("01530880521");

        List<MobileElement> empresas = driver.findElements(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresas.get(0).click();

        try {
            sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement ponto_button = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/pointt"));

        ponto_button.click();

        List<MobileElement> matriculas = driver.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_mat"));
        matriculas.get(0).click();

        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MobileElement registrar_ponto_bttn = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/pm_beat_point"));

        registrar_ponto_bttn.click();

        try {
            sleep(22000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {

            MobileElement alert = (MobileElement) driver.findElement
                    (By.id("android:id/message"));

            if (alert.isDisplayed()) {
                assertFalse(alert.getText().equals
                        ("Não foi possível obter sua localização, tente novamente em alguns minutos."));

            }
        } catch (Exception e) {
            System.out.println("Localização detectada");
        }

        MobileElement hora = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/bp_tv_data_hour"));
        String hora_str = hora.getText();
        MobileElement date = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/bp_tv_data_date"));

        String data = date.getText();
        data = data.substring(3, 5);

        String mes = meses.get(Integer.parseInt(data));


        MobileElement registrar_bttn = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/btn_beat_point"));

        registrar_bttn.click();

        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<MobileElement> meses_tv = driver.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_month"));

        MobileElement ponto_mes = meses_tv.get(0);

        for (MobileElement m : meses_tv) {
            if (m.getText().equals(mes)) {
                ponto_mes = m;
                break;

            }

        }

        ponto_mes.click();

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        boolean flag_horas_iguais = false;
        List<MobileElement> horarios = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            String id = "br.com.fortes.appcolaborador:id/tv_point_" + i;
            System.out.println(id);
            horarios.add((MobileElement) driver.findElement
                    (By.id(id)));
            String hora_econtrada = horarios.get(i).getText();

            if (hora_econtrada.equals(hora_str)) {
                flag_horas_iguais = true;
                break;
            }
        }
        if (flag_horas_iguais) {
            assert true;
        } else {
            fail("O ponto batido não foi encontrado na tabela");
        }


    }


    @Test
    public void detectar_localizacao() {

        logar_cpf("01530880521");

        List<MobileElement> empresas = driver.findElements
                (By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresas.get(4).click();

        try {
            sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement ponto_button = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/pointt"));

        ponto_button.click();

        MobileElement registrar_ponto_bttn = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/pm_beat_point"));

        registrar_ponto_bttn.click();
        Boolean flag_localizacao = false;

        try {
            sleep(22000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {

            MobileElement alert = (MobileElement) driver.findElement
                    (By.id("android:id/message"));

            if (alert.isDisplayed()) {
                assertFalse(alert.getText().equals
                        ("Não foi possível obter sua localização, tente novamente em alguns minutos."));

            }
        } catch (Exception e) {
            System.out.println("Localização detectada");
            flag_localizacao = true;
        }

        if (flag_localizacao) {
            assert true;
        } else {
            fail("Não foi possível detectar a localizaçãp em 22s");
        }
    }

}
