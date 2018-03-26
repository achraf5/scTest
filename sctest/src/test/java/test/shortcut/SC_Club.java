package test.shortcut;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import test.utile.Switch;

public class SC_Club {
	Switch switchpage = new Switch();
	Logger log = Logger.getLogger("devpinoyLogger");

	public SC_Club() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SC_Club(WebDriver driver, String xpath) throws InterruptedException {
		// test sur la SC
		WebElement sc_club = driver.findElement(By.xpath(xpath));
		if (!sc_club.isDisplayed() || !sc_club.getAttribute("class").equalsIgnoreCase("resultBlock shortcut shortcutSport")) {
			log.debug("Erreur dans l'affichage du SC SPORT");
			Assert.fail();
		}else{
			// test sur le titre du SC
			WebElement titre = driver.findElement(By.xpath(xpath + "/div[1]"));
			if (!titre.isDisplayed() || !titre.getText().equalsIgnoreCase("CERCLE SPORTIF")) {
				log.debug("Erreur dans l'affichage du titre");
				Assert.fail();
			}
			
			// test sur le lien
			WebElement lien = driver.findElement(By.xpath(xpath + "/div[2]/div[1]/a"));
			if (!lien.isDisplayed()) {
				log.debug("Erreur dans l'affichage du lien");
				Assert.fail();
			} else {
				log.debug(lien.getText());
				switchpage.testerPage(driver, lien, "sports.orange.fr", "la page renvoyé par ce lien n'est pas convenable");
			}
			
			// test sur le logo
			WebElement logo = driver.findElement(By.xpath(xpath + "/div[2]/div[2]/a/img"));
			if (!logo.isDisplayed()) {
				log.debug("Erreur dans l'affichage du logo");
				Assert.fail();
			} else {
				switchpage.testerPage(driver, logo, "sports.orange.fr","la page renvoy� par ce lien n'est pas convenable");
			}
			
			// test sur les matchs
			List<WebElement> infosSurClub = driver.findElements(By.xpath(xpath+"/div[2]/div[3]/table/tbody/tr"));
			for (int i = 0; i < infosSurClub.size(); i++) {
				if (infosSurClub.get(i).getAttribute("class").equalsIgnoreCase("large")) {
					if (!infosSurClub.get(i).isDisplayed()) {
						System.out.println("Erreur dans l'affichage des resultats");
						Assert.fail();
					} else {
						if (i == 0 || i == 2) {
							WebElement resultat = driver.findElement(
									By.xpath(xpath + "/div[2]/div[3]/table/tbody/tr[" + (i + 1) + "]/td/a"));
							switchpage.testerPage(driver, resultat, "sports.orange.fr",
									"la page renvoyé par ce lien n'est pas convenable");
						}
					}
				}
			}
			
			//test sur le footer Homepage
			WebElement homepage = driver.findElement(By.xpath(xpath + "/div[2]/div[4]"));
			if (!homepage.isDisplayed()) {
				log.debug("Erreur dans l'affichage du lien homepage");
				Assert.fail();
			}
		}
		
	}
	
	

}
