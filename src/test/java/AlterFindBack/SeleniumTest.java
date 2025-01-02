package AlterFindBack;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {
    public static void main(String[] args) {
        // Définir le chemin du WebDriver
        // System.setProperty("webdriver.chrome.driver", "D:\\Tools\\chromedriver-win64\\chromedriver.exe");

        WebDriverManager.chromedriver().setup();

        // Créer une instance du navigateur
        WebDriver driver = new ChromeDriver();

        try {
            // Ouvrir une URL
            driver.get("https://www.google.com");

            // Cliquez pour fermer la popup
            WebElement buttonPopup = driver.findElement(By.id("L2AGLb"));
            buttonPopup.click();

            // Trouver un élément par son nom
            WebElement searchBox = driver.findElement(By.name("q"));

            // Interagir avec l'élément
            searchBox.sendKeys("Selenium WebDriver Java");
            searchBox.submit();

            // Attendre quelques secondes pour voir les résultats (optionnel)
            // Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fermer le navigateur
            // driver.quit();
        }
    }
}



