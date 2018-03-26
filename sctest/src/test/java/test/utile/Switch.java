package test.utile;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Switch {
public Switch(){
		
	}
		
	public void testerPage(WebDriver driver, WebElement lien, String exp_a_verifier, String message_err) throws InterruptedException{
		//WebDriverWait wait = new WebDriverWait(driver, 20);
		String page_actuelle = driver.getWindowHandle();
		//wait.until(ExpectedConditions.visibilityOf(lien));
		lien.click();
		Thread.sleep(5000);
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		ArrayList<String> pages =  new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(pages.get(1));
		if(!driver.getCurrentUrl().contains(exp_a_verifier)){
			System.err.println(message_err);
			Assert.fail();
		}
		driver.close();
		driver.switchTo().window(page_actuelle);
	}

}
