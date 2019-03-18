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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static java.lang.Thread.sleep;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.fail;

public class TestLogin {
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

    private void logar_cpf_pass(String cpf_str, String pass_str) {
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
    public void test_cpf_invalido() {
        MobileElement cpf = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/et_cpf"));
        cpf.sendKeys("063056165151");
        MobileElement tvErrorCPF = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/tv_cpf_error"));
        String cpfInvalido = "CPF inválido";
        assertEquals(tvErrorCPF.getText(), cpfInvalido);

    }

    //CPF VALIDO NÃO CADASTRO
    @Test
    public void test_credenciais_incorretas() {
        MobileElement cpf = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/et_cpf"));
        MobileElement pass = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/et_password"));
        MobileElement login_button = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/cpf_sign_in_button"));
        MobileElement tvErrorCPF = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/tv_cpf_error"));

        String error = "Sua empresa não tem esse serviço disponível. Procure o setor de Recursos Humanos para ativá-lo";

        cpf.sendKeys("06155262330");
        pass.sendKeys("1234567789");
        login_button.click();

        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(tvErrorCPF.getText(), error);
    }

    @Test
    public void test_campos_vazios() {
        MobileElement cpf = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/et_cpf"));
        MobileElement pass = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/et_password"));
        MobileElement login_button = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/cpf_sign_in_button"));

        assertFalse(login_button.isEnabled());

    }


    @Test
    public void test_cpfvalido_senhaincorreta() {
        MobileElement cpf = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/et_cpf"));
        MobileElement pass = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/et_password"));
        MobileElement login_button = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/cpf_sign_in_button"));

        cpf.sendKeys("00717420345");
        pass.sendKeys("79654321654");

        login_button.click();

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement error_dados_alert = (MobileElement) driver.findElement(By.id("android:id/alertTitle"));
        if (!(error_dados_alert.getText().equals("CPF ou senha incorretos"))) {
            fail("Não esá exibindo o texto \"CPF ou senhas incorretos\"");
        }
        MobileElement error_dados_alert_button = (MobileElement) driver.findElement(By.id("android:id/button1"));
        error_dados_alert_button.click();


        MobileElement error_dados_tv = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/tv_login_incorrect"));
        String error_dados = "Os dados informados não correspondem a um login existente. Tente novamente.";

        MobileElement qnt_tentativas_tv = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/tv_number_attempts"));


        if (qnt_tentativas_tv.getText().contains("Você possui mais ") && qnt_tentativas_tv.getText().contains(" tentativas")) {
            assertFalse(false);
        } else {
            fail("Não está exibindo o texto de tentativas");

        }
    }


    @Test
    public void test_recuperar_senha() {
        MobileElement recover_pass_tv = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/tv_recover_password"));

        recover_pass_tv.click();

        WebDriverWait wait = new WebDriverWait(driver, 4);


        MobileElement recover_cpf_tv = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/rp_et_cpf"));

        wait.until(ExpectedConditions.visibilityOf(recover_cpf_tv));

        assert (recover_cpf_tv.isDisplayed());

    }

    @Test
    public void login_cognito_sem_empresa() {
        logar_cpf("03717612395");
        MobileElement alerta = (MobileElement) driver.findElement
                (By.id("android:id/message"));
        assertEquals("Entre em contato com um administrador e tente novamente.", alerta.getText());


    }

    @Test
    public void test_recuperar_senha_cpf_invalido() {
        MobileElement recover_pass_tv = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/tv_recover_password"));

        recover_pass_tv.click();

        WebDriverWait wait = new WebDriverWait(driver, 4);


        MobileElement recover_cpf_tv = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/rp_et_cpf"));

        wait.until(ExpectedConditions.visibilityOf(recover_cpf_tv));

        recover_cpf_tv.sendKeys("39456803975");

        MobileElement rp_tv_invalid_cpf = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/rp_tv_invalid_cpf"));

        assertEquals("CPF inválido", rp_tv_invalid_cpf.getText());

    }

    @Test
    public void test_login_correto() {
        logar();


        driver.getContext();


        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement perfil = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/profile"));

        assert (perfil.isDisplayed());


    }


    @Test
    public void test_minimizar() {
        logar();
        driver.getContext();

        MobileElement drop = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/tv_company"));
        drop.click();

        MobileElement perfil = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/profile"));


        if (perfil.isDisplayed()) {
            driver.runAppInBackground(Duration.ofSeconds(2));
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            perfil = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/profile"));
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
        MobileElement perfil = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/profile"));

        assert (perfil.isDisplayed());


    }

    @Test
    public void test_login_logout_login_diffacc() {
        logar();
        deslogar();
        logar();
        MobileElement perfil = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/profile"));

        assert (perfil.isDisplayed());


    }

    @Test
    public void solicite_primeiro_acesso() {
        logar_cpf_pass("53752313870", "02535568");
        MobileElement alert = (MobileElement) driver.findElement(By.id("android:id/alertTitle"));
        assertEquals("Solicite o primeiro acesso", alert.getText());


    }

}
