import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
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

import static java.lang.Thread.sleep;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class TestPerfil {
    AppiumDriver driver;

    @Before
    public void setup() throws MalformedURLException {
        File app = new File("/home/nivardo/nivardo/lia/automacao/", "app-homolog.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device", "Android");

        //mandatory capabilities
        capabilities.setCapability("deviceName", "Android");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("autoGrantPermissions", "true");
        //other caps
        capabilities.setCapability("app", app.getAbsolutePath());
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }


    @After
    public void tearDown() {
        driver.quit();
    }


    //TESTE DE VERIFICAÇÃO DE CPF INVALIDO
    @Test
    public void test_cpf_invalido() {
        MobileElement cpf = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/et_cpf"));
        cpf.sendKeys("063056165151");
        MobileElement tvErrorCPF = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/tv_cpf_error"));
        String cpfInvalido = "CPF inválido";
        assertEquals(tvErrorCPF.getText(), cpfInvalido);

    }

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

    //não rodar com cpf valido para nao quebrar a conta
    /*
    @Test
    public void test_cpfvalido_senhaincorreta(){
        MobileElement cpf = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/et_cpf"));
        MobileElement pass = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/et_password"));
        MobileElement login_button = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/cpf_sign_in_button"));

        cpf.sendKeys("xxxx");
        pass.sendKeys("79654321654");

        login_button.click();

        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement error_dados_tv = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/tv_login_incorrect"));
        String error_dados = "Os dados informados não correspondem a um login existente. Tente novamente.";

        MobileElement qnt_tentativas_tv = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/tv_number_attempts"));
        String error_tentativas = "Você possui mais 3 tentativas";

        if(qnt_tentativas_tv.getText().contains("Você possui mais ") && qnt_tentativas_tv.getText().contains(" tentativas")){
            assertFalse(false);
        }else{
            fail();

        }
    }
*/

    @Test
    public void test_recuperar_senha(){
        MobileElement recover_pass_tv = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/tv_recover_password"));

        recover_pass_tv.click();

        WebDriverWait wait = new WebDriverWait(driver,4);


        MobileElement recover_cpf_tv = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/rp_et_cpf"));

        wait.until(ExpectedConditions.visibilityOf(recover_cpf_tv));

        assert(recover_cpf_tv.isDisplayed());

    }


}
