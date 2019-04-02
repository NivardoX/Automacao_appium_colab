import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static java.lang.Thread.sleep;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class TestPerfil extends testBase{

    @Test
    public void test_campos_perfil() {
        logar();



        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement perfil = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/profile"));
        perfil.click();



        MobileElement scroll_view = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/scroll_view_profile"));

        List<MobileElement> elementos = driver.findElements(MobileBy
                .AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                        + "new UiSelector().className(\"android.widget.EditText\").clickable(true));"));

        int erros = 0;
        for (int i = 0; i < elementos.size(); i++) {
            if (elementos.get(i).getText().equals("")) {
                erros++;
            }
            try {
                sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        assertEquals(0, erros);

    }


    //Faltam dados.
    @Test
    public void test_campos_perfil_enderecoVazio() {
        logar_cpf("01530880521");

        List<MobileElement> empresas = driver.findElements(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresas.get(3).click();
        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MobileElement perfil = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/profile"));
        perfil.click();

        MobileElement scroll_view = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/scroll_view_profile"));

        List<MobileElement> elementos = driver.findElements(MobileBy
                .AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                        + "new UiSelector().className(\"android.widget.EditText\").clickable(true));"));

        int erros = 0;
        for (int i = 0; i < elementos.size(); i++) {
            if (elementos.get(i).getText().equals("")) {
                erros++;
            }
            try {
                sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        assert (erros >= 1);

    }

    @Test
    public void test_mudar_empresa_campos() {
        logar();

        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MobileElement perfil = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/profile"));
        perfil.click();

        MobileElement scroll_view = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/scroll_view_profile"));

        MobileElement elemento = (MobileElement) driver.findElement(MobileBy
                .AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                        + "new UiSelector().className(\"android.widget.EditText\").clickable(true).index(3));"));

        String cpf_1 = elemento.getText();

        System.out.println(cpf_1);


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

        logar_cpf("01607344521");


        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        perfil = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/profile"));
        perfil.click();

        elemento = (MobileElement) driver.findElement(MobileBy
                .AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                        + "new UiSelector().className(\"android.widget.EditText\").clickable(true).index(3));"));

        assertFalse(cpf_1.equals(elemento.getText()));

    }

}
