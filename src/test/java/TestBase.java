import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

public class TestBase {
    static AppiumDriver driver;
    Double CONST_NET = 1.3;
    String PATH = System.getProperty("user.dir") + "/";
    WebDriverWait wait;

    @Rule
    public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule();

    @Before
    public void setup() throws MalformedURLException {


        File app = new File(PATH, "app-homolog_ifce.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device", "Android");

        //mandatory capabilities
        capabilities.setCapability("deviceName", "Mi 8 Lite");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("autoGrantPermissions", "true");
        capabilities.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, "true");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        //other caps
        capabilities.setCapability("app", app.getAbsolutePath());
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        wait = new WebDriverWait(driver, 30);

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
        MobileElement dropDown = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/iv_company")));

        dropDown.click();
        sleep_testes(1000 * CONST_NET);


        List<MobileElement> empresas = driver.findElements
                (By.id("br.com.fortes.appcolaborador:id/constraint_layout_profile"));
        empresas.get(2).click();
        sleep_testes(2000 * CONST_NET);

    }

    void logar() {

        MobileElement cpf = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/et_cpf")));
        MobileElement pass = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/et_password")));
        MobileElement login_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/cpf_sign_in_button")));


        cpf.sendKeys("00717420345");
        pass.sendKeys("12345678");
        login_button.click();
        sleep_testes(6000 * CONST_NET);

    }

    void logar_cpf_pass(String cpf_str, String pass_str) {
        MobileElement cpf = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/et_cpf")));
        MobileElement pass = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/et_password")));
        MobileElement login_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/cpf_sign_in_button")));


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
        MobileElement cpf = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/et_cpf")));
        cpf.sendKeys(cpf_str);

        MobileElement pass = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/et_password")));
        pass.sendKeys("12345678");

        MobileElement login_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/cpf_sign_in_button")));

        login_button.click();
        sleep_testes(7000 * CONST_NET);

    }

    void deslogar() {

        MobileElement perfil = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/profile")));
        perfil.click();

        Dimension size = driver.manage().window().getSize();

        MobileElement scroll_view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("br.com.fortes.appcolaborador:id/scroll_view_profile"))));
        int startY = (int) (size.height * 0.70);
        int endY = (int) (size.height * 0.10);

        new TouchAction(driver).press(PointOption.point(550, startY)).waitAction().moveTo(PointOption.point(550, endY)).release().perform();
        MobileElement logout_btn2 = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("br.com.fortes.appcolaborador:id/btn_logout"))));

        logout_btn2.click();
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

    @AfterClass
    public static void tearDown() {driver.quit();
    }

    class ScreenshotTestRule implements MethodRule {
        public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object o) {

            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    try {
                        statement.evaluate();
                    } catch (Throwable t) {
                        String fileName = frameworkMethod.getName();
                        new File("screenshots").mkdirs(); // Insure directory is there
                        FileOutputStream out = new FileOutputStream("screenshots/screenshot-" + fileName + ".png");
                        out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
                        out.close();


                        throw t; // rethrow to allow the failure to be reported to JUnit
                    }
                }

            };
        }
    }

}
