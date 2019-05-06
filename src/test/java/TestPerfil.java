import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class TestPerfil extends TestBase {

    @Test
    public void test_campos_perfil() {
        logar();


        sleep_testes(6000 * CONST_NET);

        MobileElement perfil = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/profile")));
        perfil.click();


        MobileElement scroll_view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/scroll_view_profile")));

        List<MobileElement> elementos = driver.findElements(MobileBy
                .AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                        + "new UiSelector().className(\"android.widget.EditText\").clickable(true));"));

        int erros = 0;
        for (int i = 0; i < elementos.size(); i++) {
            if (elementos.get(i).getText().equals("")) {
                erros++;
            }
            sleep_testes(300 * CONST_NET);

        }

        assertEquals(0, erros);

    }


    //Faltam dados.
    @Test
    public void test_campos_perfil_enderecoVazio() {
        logar_cpf("01530880521");

        List<MobileElement> empresas = driver.findElements(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresas.get(3).click();
        sleep_testes(6000 * CONST_NET);
        MobileElement perfil = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/profile")));
        perfil.click();

        MobileElement scroll_view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/scroll_view_profile")));

        List<MobileElement> elementos = driver.findElements(MobileBy
                .AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                        + "new UiSelector().className(\"android.widget.EditText\").clickable(true));"));

        int erros = 0;
        for (int i = 0; i < elementos.size(); i++) {
            if (elementos.get(i).getText().equals("")) {
                erros++;
            }
            sleep_testes(300 * CONST_NET);

        }

        assert (erros >= 1);

    }

    @Test
    public void test_mudar_empresa_campos() {
        logar();

        sleep_testes(6000 * CONST_NET);
        MobileElement perfil = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/profile")));
        perfil.click();

        MobileElement scroll_view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/scroll_view_profile")));

        MobileElement elemento = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy
                .AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                        + "new UiSelector().className(\"android.widget.EditText\").clickable(true).index(3));")));

        String cpf_1 = elemento.getText();

        System.out.println(cpf_1);


        MobileElement logout_btn = scroll_view
                .findElement(MobileBy
                        .AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                                + "new UiSelector().text(\"Sair\"));"));

        sleep_testes(1000 * CONST_NET);
        logout_btn = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("br.com.fortes.appcolaborador:id/btn_logout"))));

        logout_btn.click();

        sleep_testes(1000 * CONST_NET);

        logar_cpf("01607344521");


        sleep_testes(6000 * CONST_NET);

        perfil = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/profile")));
        perfil.click();

        elemento = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy
                .AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                        + "new UiSelector().className(\"android.widget.EditText\").clickable(true).index(3));")));

        assertFalse(cpf_1.equals(elemento.getText()));

    }

}
