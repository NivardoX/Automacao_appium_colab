import io.appium.java_client.MobileElement;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.fail;

public class TestLogin extends TestBase {


    @Test
    public void test_cpf_invalido() {
        MobileElement cpf = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/et_cpf")));
        cpf.sendKeys("063056165151");
        MobileElement tvErrorCPF = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/tv_cpf_error")));
        String cpfInvalido = "CPF inválido";
        assertEquals(tvErrorCPF.getText(), cpfInvalido);

    }

    //CPF VALIDO NÃO CADASTRO
    @Test
    public void test_credenciais_incorretas() {
        MobileElement cpf = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/et_cpf")));
        MobileElement pass = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/et_password")));
        MobileElement login_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/cpf_sign_in_button")));

        String error = "Sua empresa não tem esse serviço disponível. Procure o setor de Recursos Humanos para ativá-lo";

        cpf.sendKeys("06155262330");
        pass.sendKeys("1234567789");
        login_button.click();
        MobileElement tvErrorCPF = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/tv_cpf_error")));
        String tvErrorCPFtext = tvErrorCPF.getText();

        assertEquals(error,tvErrorCPFtext);
    }

    @Test
    public void test_campos_vazios() {
        MobileElement cpf = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/et_cpf")));
        MobileElement pass = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/et_password")));
        MobileElement login_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/cpf_sign_in_button")));

        assertFalse(login_button.isEnabled());

    }


    @Test
    public void test_cpfvalido_senhaincorreta() {
        MobileElement cpf = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/et_cpf")));
        MobileElement pass = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/et_password")));
        MobileElement login_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/cpf_sign_in_button")));

        cpf.sendKeys("00717420345");
        pass.sendKeys("79654321654");

        login_button.click();

        sleep_testes(2000 * CONST_NET);


        MobileElement error_dados_alert = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/alertTitle")));
        if (!(error_dados_alert.getText().equals("CPF ou senha incorretos"))) {
            fail("Não está exibindo o texto \"CPF ou senha incorretos\" e sim \"" + error_dados_alert.getText() + "\"");
        }
        MobileElement error_dados_alert_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/button1")));
        error_dados_alert_button.click();


        MobileElement error_dados_tv = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/tv_login_incorrect")));
        String error_dados = "Os dados informados não correspondem a um login existente. Tente novamente.";

        MobileElement qnt_tentativas_tv = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/tv_number_attempts")));


        if (qnt_tentativas_tv.getText().contains("Você possui mais ") && qnt_tentativas_tv.getText().contains(" tentativas")) {
            assertFalse(false);
        } else {
            fail("Não está exibindo o texto de tentativas");

        }
    }


    @Test
    public void test_recuperar_senha() {
        MobileElement recover_pass_tv = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/tv_recover_password")));

        recover_pass_tv.click();

        sleep_testes(500 * CONST_NET);


        MobileElement recover_cpf_tv = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/rp_et_cpf")));

        assert (recover_cpf_tv.isDisplayed());

    }

    @Test
    public void test_recuperar_senha_minimizar_loading() {
        MobileElement recover_pass_tv = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/tv_recover_password")));

        recover_pass_tv.click();

        sleep_testes(500 * CONST_NET);

        MobileElement recover_cpf_tv = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/rp_et_cpf")));


        recover_cpf_tv.sendKeys("87577440094");

        MobileElement avancar_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/rp_bt_proceed")));
        avancar_button.click();

        driver.runAppInBackground(Duration.ofMillis(3000));
        driver.launchApp();

        sleep_testes(1000 * CONST_NET);
        MobileElement cpf = null;

        Boolean assertionFlag = false;
        try {
            cpf = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/message")));
        } catch (Exception e) {
            assertionFlag = true;
        }

        assert assertionFlag;


    }

    @Test
    public void test_recuperar_senha_minimizar() {
        MobileElement recover_pass_tv = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/tv_recover_password")));

        recover_pass_tv.click();

        sleep_testes(1000 * CONST_NET);


        MobileElement recover_cpf_tv = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/rp_et_cpf")));


        recover_cpf_tv.sendKeys("87577440094");

        MobileElement avancar_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/rp_bt_proceed")));
        avancar_button.click();


        driver.runAppInBackground(Duration.ofSeconds(2));
        //driver.launchApp();
        sleep_testes(1000 * CONST_NET);

        avancar_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/rp_bt_proceed2")));

        assert avancar_button.isDisplayed();


    }


    @Test
    public void login_cognito_sem_empresa() {
        logar_cpf("03717612395");
        MobileElement alerta = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("android:id/message")));
        assertEquals("Entre em contato com um administrador e tente novamente.", alerta.getText());


    }

    @Test
    public void test_recuperar_senha_cpf_invalido() {
        MobileElement recover_pass_tv = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/tv_recover_password")));

        recover_pass_tv.click();

        sleep_testes(2000 * CONST_NET);


        MobileElement recover_cpf_tv = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/rp_et_cpf")));
        sleep_testes(2000 * CONST_NET);


        recover_cpf_tv.sendKeys("39456803975");

        MobileElement rp_tv_invalid_cpf = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/rp_tv_invalid_cpf")));

        assertEquals("CPF inválido", rp_tv_invalid_cpf.getText());

    }

    @Test
    public void test_login_correto() {
        logar();


        driver.getContext();


        sleep_testes(8000 * CONST_NET);

        MobileElement perfil = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/profile")));

        assert (perfil.isDisplayed());


    }


    @Test
    public void test_minimizar() {
        logar();
        driver.getContext();
        sleep_testes(8000 * CONST_NET);

        MobileElement perfil = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/profile")));


        if (perfil.isDisplayed()) {
            driver.runAppInBackground(Duration.ofSeconds(2));


            sleep_testes(2000 * CONST_NET);

            perfil = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/profile")));
            assert (perfil.isDisplayed());


        } else {
            fail("Nao está exibindo o profile");
        }


    }

    @Test
    public void test_login_logout_login_sameacc() {
        logar();
        deslogar();
        logar_cpf("01607344521");
        MobileElement perfil = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/profile")));

        assert (perfil.isDisplayed());


    }

    @Test
    public void test_login_logout_login_diffacc() {
        logar();
        sleep_testes(4000 * CONST_NET);

        deslogar();
        sleep_testes(4000 * CONST_NET);

        logar();
        sleep_testes(6000 * CONST_NET);

        MobileElement perfil = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/profile")));

        assert (perfil.isDisplayed());


    }

    @Test
    public void solicite_primeiro_acesso() {
        logar_cpf_pass("53752313870", "02535568");
        MobileElement alert = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/alertTitle")));
        assertEquals("Solicite o primeiro acesso", alert.getText());


    }

}
