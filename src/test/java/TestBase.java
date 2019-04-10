import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

public class TestBase {
    AppiumDriver driver;
    Double CONST_NET = 1.2;
    String PATH = "/home/nivardo/nivardo/lia/automacao/";

    @Before
    public void setup() throws MalformedURLException {


        File app = new File(PATH, "app-homolog_ifce.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device", "Android");

        //mandatory capabilities
        capabilities.setCapability("deviceName", "Mi 8 Lite");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("autoGrantPermissions", "true");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        //other caps
        capabilities.setCapability("app", app.getAbsolutePath());
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    public void sleep_testes(Double millis) {
        long mils = (millis.longValue());
        try {
            sleep(mils);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void enviar_req(String file, String endpoint) {
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(PATH + "src/test/java/" + file);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            String payload = "";
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                payload += line;
            }

            System.out.println(payload);
            StringEntity entity = new StringEntity(payload,
                    "UTF-8");

            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost("https://qe00nlgco8.execute-api.sa-east-1.amazonaws.com/homologifce/" + endpoint);
            request.setEntity(entity);

            HttpResponse response = httpClient.execute(request);
            System.out.println(response.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void trocar_empresa() {
        MobileElement dropDown = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/iv_company"));

        dropDown.click();
        sleep_testes(1000 * CONST_NET);


        List<MobileElement> empresas = driver.findElements
                (By.id("br.com.fortes.appcolaborador:id/constraint_layout_profile"));
        empresas.get(2).click();
        sleep_testes(2000 * CONST_NET);

    }

    void logar() {
        MobileElement cpf = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/et_cpf"));
        MobileElement pass = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/et_password"));
        MobileElement login_button = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/cpf_sign_in_button"));


        cpf.sendKeys("00717420345");
        pass.sendKeys("12345678");
        login_button.click();
        sleep_testes(6000 * CONST_NET);

    }

    void logar_cpf_pass(String cpf_str, String pass_str) {
        MobileElement cpf = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/et_cpf"));
        MobileElement pass = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/et_password"));
        MobileElement login_button = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/cpf_sign_in_button"));


        cpf.sendKeys(cpf_str);
        pass.sendKeys(pass_str);
        login_button.click();
        sleep_testes(6000 * CONST_NET);

    }

    Boolean isSorted(List<MobileElement> a) {
        int i = 0;
        if (a.size() == 1) {
            return true;
        }
        for (i = 0; i < a.size(); i++) {
            return Integer.parseInt(a.get(i).getText()) < Integer.parseInt(a.get(i + 1).getText());
        }
        return true;
    }

    void logar_cpf(String cpf_str) {
        MobileElement cpf = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/et_cpf"));
        MobileElement pass = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/et_password"));
        MobileElement login_button = (MobileElement) driver.findElement
                (By.id("br.com.fortes.appcolaborador:id/cpf_sign_in_button"));


        cpf.sendKeys(cpf_str);
        pass.sendKeys("12345678");
        login_button.click();
        sleep_testes(7000 * CONST_NET);

    }

    void deslogar() {
        sleep_testes(3000 * CONST_NET);

        MobileElement perfil = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/profile"));
        perfil.click();

        sleep_testes(1000 * CONST_NET);


        MobileElement scroll_view = (MobileElement) driver.findElement((By.id("br.com.fortes.appcolaborador:id/scroll_view_profile")));

        MobileElement logout_btn = scroll_view
                .findElement(MobileBy
                        .AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                                + "new UiSelector().text(\"Sair\"));"));

        sleep_testes(1000 * CONST_NET);

        logout_btn = (MobileElement) driver.findElement((By.id("br.com.fortes.appcolaborador:id/btn_logout")));

        logout_btn.click();
        sleep_testes(1000 * CONST_NET);


    }


    void atualizar() {

        driver.getContext();
        //Get the size of screen.
        Dimension size = driver.manage().window().getSize();
//Find swipe start and end point from screen’s width and height.
//Find starty point which is at bottom side of screen.
        int startY = (int) (size.height * 0.90);
//Find endy point which is at top side of screen.
        int endY = (int) (size.height * 0.40);
//Find horizontal point where you wants to swipe. It is in middle of screen width.
        int startX = size.width / 2;
//Swipe from Bottom to Top. driver.swipe(startx, starty, startx, endy, 3000 * CONST_NET);
        sleep_testes(1000 * CONST_NET);

//Swipe from Top to Bottom.

        new TouchAction(driver)
                .press(PointOption.point(startX, endY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
                .moveTo(PointOption.point(startX, startY))
                .release()
                .perform();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
