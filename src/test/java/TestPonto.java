import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;

public class TestPonto extends TestBase {

    @Test
    public void test_ponto_exibir_matriculas() {

        logar();


        MobileElement ponto_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/point")));

        ponto_button.click();

        List<MobileElement> rc_matriculas = driver.findElements
                (By.id("br.com.fortes.appcolaborador:id/constraint_layout_profile"));

        assert (rc_matriculas.size() > 1);

    }

    @Test
    public void test_ponto_mes_ano() {


        logar();


        MobileElement ponto_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/point")));

        ponto_button.click();

        MobileElement rc_matricula = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/constraint_layout_profile")));

        rc_matricula.click();


        MobileElement scroll_view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/point_month_recyclerview")));

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
    public void test_ponto_refresh() {

        logar();


        MobileElement ponto_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/point")));

        ponto_button.click();

        List<MobileElement> rc_matriculas = driver.findElements
                (By.id("br.com.fortes.appcolaborador:id/constraint_layout_profile"));

        rc_matriculas.get(0).click();


        sleep_testes(400 * CONST_NET);

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


        MobileElement toolbar = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/toolbar")));
        MobileElement title = toolbar.findElement(By.className("android.widget.TextView"));

        assertEquals("Histórico de Batidas", title.getText());


    }

    @Test
    public void test_ponto_att_dados_troca_empresa() {

        logar_cpf("01530880521");

        MobileElement empresa = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/tv_name_company")));
        empresa.click();


        MobileElement ponto_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/point")));

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
    public void test_ponto_empty_state() {

        logar_cpf("01530880521");


        List<MobileElement> empresas = driver.findElements(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresas.get(1).click();


        MobileElement ponto_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/point")));

        ponto_button.click();

        sleep_testes(500 * CONST_NET);

        try {
            MobileElement nothing_point = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("br.com.fortes.appcolaborador:id/nothing_point")));
        } catch (Exception e) {
            assert (false);
        }

        assert true;


    }

    @Test
    public void test_ponto_bater_ponto() {

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


        MobileElement ponto_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/point")));

        ponto_button.click();

        MobileElement registrar_ponto_bttn = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/pm_beat_point")));

        registrar_ponto_bttn.click();


        try {

            MobileElement alert = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                    (By.id("android:id/message")));

            if (alert.isDisplayed()) {
                assertFalse(alert.getText().equals
                        ("Não foi possível obter sua localização, tente novamente em alguns minutos."));

            }
        } catch (Exception e) {
            System.out.println("Localização detectada");
        }

        MobileElement hora = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/bp_tv_data_hour")));
        String hora_str = hora.getText();
        MobileElement date = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/bp_tv_data_date")));

        String data = date.getText();
        data = data.substring(3, 5);

        String mes = meses.get(Integer.parseInt(data));


        MobileElement registrar_bttn = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/btn_beat_point")));

        registrar_bttn.click();

        sleep_testes(6000 * CONST_NET);


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


        sleep_testes(2000 * CONST_NET);


        boolean flag_horas_iguais = false;
        List<MobileElement> horarios = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            String id = "br.com.fortes.appcolaborador:id/tv_point_" + i;
            System.out.println(id);
            horarios.add((MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                    (By.id(id))));
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
    public void test_ponto_registrar_ponto_vazio() {

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


        MobileElement ponto_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/point")));

        ponto_button.click();


        MobileElement registrar_ponto_bttn = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/pm_beat_point")));

        registrar_ponto_bttn.click();


        try {

            MobileElement alert = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                    (By.id("android:id/message")));

            if (alert.isDisplayed()) {
                assertFalse(alert.getText().equals
                        ("Não foi possível obter sua localização, tente novamente em alguns minutos."));

            }
        } catch (Exception e) {
            System.out.println("Localização detectada");
        }

        MobileElement hora = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/bp_tv_data_hour")));
        String hora_str = hora.getText();
        MobileElement date = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/bp_tv_data_date")));

        String data = date.getText();
        data = data.substring(3, 5);

        String mes = meses.get(Integer.parseInt(data));


        MobileElement registrar_bttn = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/btn_beat_point")));

        registrar_bttn.click();


        sleep_testes(9000 * CONST_NET);


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

        sleep_testes(2000 * CONST_NET);


        boolean flag_horas_iguais = false;
        List<MobileElement> horarios = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            String id = "br.com.fortes.appcolaborador:id/tv_point_" + i;
            System.out.println(id);
            horarios.add((MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                    (By.id(id))));
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
    public void test_ponto_registrar_ponto_matriculas() {

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


        sleep_testes(6000 * CONST_NET);

        List<MobileElement> empresas = driver.findElements(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresas.get(0).click();


        MobileElement ponto_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/point")));

        ponto_button.click();

        List<MobileElement> matriculas = driver.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_mat"));
        matriculas.get(0).click();


        MobileElement registrar_ponto_bttn = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/pm_beat_point")));

        registrar_ponto_bttn.click();

        sleep_testes(22000 * CONST_NET);

        try {

            MobileElement alert = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                    (By.id("android:id/message")));

            if (alert.isDisplayed()) {
                assertFalse(alert.getText().equals
                        ("Não foi possível obter sua localização, tente novamente em alguns minutos."));

            }
        } catch (Exception e) {
            System.out.println("Localização detectada");
        }

        MobileElement hora = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/bp_tv_data_hour")));
        String hora_str = hora.getText();
        MobileElement date = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/bp_tv_data_date")));

        String data = date.getText();
        data = data.substring(3, 5);

        String mes = meses.get(Integer.parseInt(data));


        MobileElement registrar_bttn = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/btn_beat_point")));

        registrar_bttn.click();


        sleep_testes(6000 * CONST_NET);


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


        sleep_testes(2000 * CONST_NET);


        boolean flag_horas_iguais = false;
        List<MobileElement> horarios = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            String id = "br.com.fortes.appcolaborador:id/tv_point_" + i;
            System.out.println(id);
            horarios.add((MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                    (By.id(id))));
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
    public void test_ponto_detectar_localizacao() {

        logar_cpf("01530880521");

        List<MobileElement> empresas = driver.findElements
                (By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresas.get(4).click();


        MobileElement ponto_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/point")));

        ponto_button.click();

        MobileElement registrar_ponto_bttn = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/pm_beat_point")));

        registrar_ponto_bttn.click();
        Boolean flag_localizacao = false;


        sleep_testes(22000 * CONST_NET);

        try {

            MobileElement alert = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                    (By.id("android:id/message")));

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

    @Test
    public void test_ponto_dataSaldo_descr() {
        enviar_req("enviarSaldo.json", "agente/ponto/espelho/incluir");
        logar_cpf("01846903580");


        MobileElement ponto_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/point")));

        ponto_button.click();

        List<MobileElement> rc_matriculas = driver.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_mat"));
        for (MobileElement rc_matricula : rc_matriculas) {
            System.out.println(rc_matricula.getText());
            if (rc_matricula.getText().contains("012643")) {
                rc_matricula.click();
                break;
            }
        }
        sleep_testes(500 * CONST_NET);

        List<MobileElement> meses_tv = null;
        meses_tv = driver.findElements(By.id("br.com.fortes.appcolaborador:id/text_month"));


        if (meses_tv.size() == 0) {
            meses_tv = driver.findElements
                    (By.id("br.com.fortes.appcolaborador:id/text_month_single"));
        }

        for (MobileElement mes : meses_tv) {
            System.out.println(mes.getText());
            if (mes.getText().equals("Janeiro")) {
                mes.click();
                break;
            }

        }

        sleep_testes(1000 * CONST_NET);

        MobileElement saldo_banco = (MobileElement) driver.findElementById("br.com.fortes.appcolaborador:id/saldo_banco_horas_atedia");
        enviar_req("excluirBatida.json", "agente/ponto/espelho/excluir");
        assert (saldo_banco.getText().contains("Teste Banco de Horas") && saldo_banco.getText().contains("31/12/2017"));
    }

}
