package test.shortcut;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import test.utile.Switch;

public class SC_Meteo {
	Switch switchpage = new Switch();
	Logger log = Logger.getLogger("devpinoyLogger");

	public SC_Meteo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SC_Meteo(WebDriver driver, String xpath) throws InterruptedException {
		//test sur la SC
		WebElement sc_meteo = driver.findElement(By.xpath(xpath));
		if(!sc_meteo.isDisplayed() || !sc_meteo.getAttribute("class").equalsIgnoreCase("resultBlock shortcut shortcutMeteo")){
			log.debug("Erreur dans l'affichage du SC METEO");
			Assert.fail();
		}else{
			//test sur le titre du bloc
			WebElement titre = driver.findElement(By.xpath(xpath+"/div[1]"));
			if(!titre.isDisplayed() || !titre.getText().equalsIgnoreCase("QUEL TEMPS FAIT-IL ?")){
				log.debug("Erreur dans l'affichage du titre du SC");
				Assert.fail();
			}
			
			//test sur le lien du SC
			WebElement lien = driver.findElement(By.xpath(xpath+"/div[2]/a"));
			if(!lien.isDisplayed()){
				log.debug("Erreur dans l'affichage du titre du lien");
				Assert.fail();
			}else{
				switchpage.testerPage(driver, lien, "meteo.orange.fr", "la page renvoy� par ce lien n'est pas convenable");
			}
			
			//test sur le temps
			List<WebElement> meteosJour = driver.findElements(By.xpath(xpath+"/div[2]/ul/li"));
			for(int i=0; i<meteosJour.size();i++){
				//test sur l'image jour
				WebElement pictojour = driver.findElement(By.xpath(xpath+"/div[2]/ul/li["+(i+1)+"]/a/div"));
				if(!pictojour.isDisplayed()){
					log.debug("Erreur dans l'affichage du logo du meto jour");
					Assert.fail();
				}else{
					switchpage.testerPage(driver, pictojour, "meteo.orange.fr", "la page renvoyé par ce lien n'est pas convenable");
				}
				
				//test sur la periode du jour
				WebElement periode = driver.findElement(By.xpath(xpath+"/div[2]/ul/li["+(i+1)+"]/div[1]"));
				if(!periode.isDisplayed()){
					log.debug("Erreur dans l'affichage du periode du jour");
					Assert.fail();
				}
				
				//test sur la degre
				WebElement degre = driver.findElement(By.xpath(xpath+"/div[2]/ul/li["+(i+1)+"]/div[1]/span[1]"));
				if(!degre.isDisplayed()){
					log.debug("Erreur dans l'affichage du degre");
					Assert.fail();
				}
				
				//test sur le desciptif
				WebElement descriptif = driver.findElement(By.xpath(xpath+"/div[2]/ul/li["+(i+1)+"]/div[1]/span[2]"));
				if(!descriptif.isDisplayed()){
					log.debug("Erreur dans l'affichage du descriptif");
					Assert.fail();
				}
			}
			
			//test sur le footer homepage
			WebElement footer = driver.findElement(By.xpath(xpath+"/div[2]/div"));
			if(!footer.isDisplayed()){
				log.debug("Erreur dans l'affichage du descriptif");
				Assert.fail();
			}
		}
	}

}
