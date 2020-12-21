import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class Testes {
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdrive.chrome.drive", "chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void verificarTitulo() throws InterruptedException {
        driver.get("https://suap.ifpb.edu.br/accounts/login/?next=/");
        Assert.assertTrue("Título incorreto",
                driver.getTitle().contentEquals("SUAP: Sistema Unificado de Administração Pública - Login"));
        Thread.sleep(1000L);
    }

    @Test
    public void testarLogin() throws InterruptedException {
        driver.get("https://suap.ifpb.edu.br/accounts/login/?next=/");
        WebElement element = driver.findElement(By.id("id_username"));
        Thread.sleep(1000L);
        element.sendKeys("loginErrado");
        Thread.sleep(2000L);
        element = driver.findElement(By.id("id_password"));
        element.sendKeys("senhaErrada");
        Thread.sleep(2000L);
        element = driver.findElement(By.className("submit-row"));
        element.click();
        Thread.sleep(2000L);
        element = driver.findElement(By.className("errornote"));

        Assert.assertEquals("Por favor, entre com um usuário e senha corretos. Note que ambos os campos diferenciam maiúsculas e minúsculas.",
                element.getText());
    }

    @Test
    public void testaBotaoProcessosFisicos() throws InterruptedException {
        driver.get("https://suap.ifpb.edu.br/accounts/login/?next=/");
        WebElement element = driver.findElement(By.linkText("Consulta de Processos Físicos"));
        Thread.sleep(1000L);
        element.click();
        assertEquals("https://suap.ifpb.edu.br/protocolo/consulta_publica/", driver.getCurrentUrl());
        Thread.sleep(2000L);
    }

    @Test
    public void testaBackgroundColor() throws InterruptedException {
        driver.get("https://suap.ifpb.edu.br/accounts/login/?next=/");
        WebElement element = driver.findElement(By.className("login"));
        assertEquals("rgba(85, 85, 85, 1)", element.getCssValue("background-color"));
        Thread.sleep(1000L);
        element = driver.findElement(By.className("login-form"));
        assertEquals("rgba(238, 238, 238, 1)", element.getCssValue("background-color"));
        Thread.sleep(1000L);
        element = driver.findElement(By.id("content"));
        assertEquals("rgba(51, 51, 51, 1)", element.getCssValue("background-color"));
        Thread.sleep(1000L);
    }
}
