package test.shortcut;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import test.utile.Switch;

public class SC_Sport {
	
	Switch switchpage = new Switch();
	Logger log = Logger.getLogger("devpinoyLogger");

	public SC_Sport() {
		super();
	}
	
	public SC_Sport(WebDriver driver, String xpath) throws InterruptedException{
		// test sur la SC
		WebElement sc_Sport = driver.findElement(By.xpath(xpath));
		if (!sc_Sport.isDisplayed()
				|| !sc_Sport.getAttribute("class").equalsIgnoreCase("resultBlock shortcut shortcutSport")) {
			log.debug("Erreur dans l'affichage du SC SPORT");
			Assert.fail();
		}else{
			// test sur le titre du SC
			WebElement titre = driver.findElement(By.xpath(xpath + "/div[1]"));
			if (!titre.isDisplayed() || !titre.getText().equalsIgnoreCase("SPORT")) {
				log.debug("Erreur dans l'affichage du titre");
				Assert.fail();
			}
			
			//test sur le lien
			WebElement lien = driver.findElement(By.xpath(xpath+"/div[2]/div[1]/a"));
			if(!lien.isDisplayed()){
				log.debug("Erreur dans l'affichage du lien");
				Assert.fail();
			}else{
				switchpage.testerPage(driver, lien, "sports.orange.fr", "la page renvoy� par ce lien n'est pas convenable");
			}
			
			//test sur le logo
			WebElement logo = driver.findElement(By.xpath(xpath+"/div[2]/div[2]/a/div"));
			if(!logo.isDisplayed()){
				log.debug("Erreur dans l'affichage du lien");
				Assert.fail();
			}else{
				switchpage.testerPage(driver, logo, "sports.orange.fr", "la page renvoyé par ce lien n'est pas convenable");
			}
			
			//test sur le classement
			List<WebElement> classements = driver.findElements(By.xpath(xpath+"/div[2]/div[3]/ul"));
			for(int i=0;i<classements.size();i++){
				/*List<WebElement> res = driver.findElements(By.xpath(xpath+"/div[2]/div[3]/ul["+(i+1)+"]/div"));
				for(int j=0;j<res.size();j++){
					if(!res.get(j).isDisplayed()){
						System.out.println("Erreur dans l'affichage des resultats");
						Assert.fail();
					}
				}*/
				if(!classements.get(i).isDisplayed()){
					System.out.println("Erreur dans l'affichage des resultats");
					Assert.fail();
				}
			}
			
			//test sur le lien tous le classement
			WebElement classement = driver.findElement(By.xpath(xpath+"/div[2]/div[3]/a"));
			if(!classement.isDisplayed()){
				log.debug("Erreur dans l'affichage du lien classement");
				Assert.fail();
			}else{
				switchpage.testerPage(driver, classement, "sports.orange.fr", "la page renvoy� par ce lien n'est pas convenable");
			}
			WebElement homepage = driver.findElement(By.xpath(xpath+"/div[2]/div[4]"));
			if(!homepage.isDisplayed()){
				log.debug("Erreur dans l'affichage du lien homepage");
				Assert.fail();
			}
		}
		
	}

}
