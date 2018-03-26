package test.shortcut;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import test.utile.Switch;

public class SC_Renseignement {
	Switch switchpage = new Switch();
	Logger log = Logger.getLogger("devpinoyLogger");

	public SC_Renseignement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SC_Renseignement(WebDriver driver, String xpath) throws InterruptedException {
		//test sur la SC
		WebElement sc_renseignement = driver.findElement(By.xpath(xpath));
		if(!sc_renseignement.isDisplayed() || !sc_renseignement.getAttribute("class").equalsIgnoreCase("resultBlock shortcut shortcutRns")){
			log.debug("Erreur dans l'affichage du SC ANNUAIRE");
			Assert.fail();
		}else{
			//test sur le bloc du titre
			//test sur le logo 118712
			WebElement logo118712 = driver.findElement(By.xpath(xpath+"/div[1]/a"));
			if(!logo118712.isDisplayed()){
				log.debug("Erreur dans l'affichage du SC ANNUAIRE");
				Assert.fail();
			}else{
				switchpage.testerPage(driver, logo118712, "www.118712.fr", "la page renvoy� par ce logo n'est pas convenable");
			}
			//test sur le titre
			WebElement titre = driver.findElement(By.xpath(xpath+"/div[1]/span[1]"));
			if(!titre.isDisplayed() || !titre.getText().equalsIgnoreCase("ANNUAIRE")){
				log.debug("Erreur dans l'affichage du titre ANNUAIRE");
				Assert.fail();
			}
			//test sur le soustitre
			WebElement soustitre = driver.findElement(By.xpath(xpath+"/div[1]/span[2]"));
			if(!soustitre.isDisplayed() || !soustitre.getText().equalsIgnoreCase("service de recherche de professionnels ou particuliers")){
				log.debug("Erreur dans l'affichage du sout-titre ANNUAIRE");
				Assert.fail();
			}
			
			//test sur le bloc des resultats
			//test sur l'affichge du carte
			WebElement map = driver.findElement(By.xpath(xpath+"/div[2]/div[1]/a/img"));
			if(!logo118712.isDisplayed()){
				log.debug("Erreur dans l'affichage du map");
				Assert.fail();
			}else{
				switchpage.testerPage(driver, map, "annuaire.118712.fr", "la page renvoyé par ce logo n'est pas convenable");
			}
			
			//test sur les resultats
			List<WebElement> res = driver.findElements(By.xpath(xpath+"/div[2]/div[2]/div"));
			for(int i=0;i<res.size();i++){
				//test sur la numerotation
				WebElement numerotation = driver.findElement(By.xpath(xpath+"/div[2]/div[2]/div["+(i+1)+"]/div[1]"));
				if(!numerotation.isDisplayed()){
					log.debug("Erreur dans l'affichage du numerotation");
					Assert.fail();
				}
				//test sur le lien de resultat
				WebElement lienRes = driver.findElement(By.xpath(xpath+"/div[2]/div[2]/div["+(i+1)+"]/div[2]/span[1]/a"));
				if(!lienRes.isDisplayed()){
					log.debug("Erreur dans l'affichage du lien de resultas");
					Assert.fail();
				}else{
					switchpage.testerPage(driver, lienRes, "annuaire.118712.fr", "la page renvoyé par ce lien de resultat n'est pas convenable");
				}
				//test sur la fil d'ariane
				WebElement ariane = driver.findElement(By.xpath(xpath+"/div[2]/div[2]/div["+(i+1)+"]/div[2]/span[2]"));
				if(!ariane.isDisplayed()){
					log.debug("Erreur dans l'affichage du file d'ariane");
					Assert.fail();
				}
				//test sur l'adresse
				WebElement adresse = driver.findElement(By.xpath(xpath+"/div[2]/div[2]/div["+(i+1)+"]/div[2]/span[3]"));
				if(!adresse.isDisplayed()){
					log.debug("Erreur dans l'affichage d'adresse");
				}
				//test sur le code postale
				WebElement codePostale = driver.findElement(By.xpath(xpath+"/div[2]/div[2]/div["+(i+1)+"]/div[2]/span[4]/span[2]"));
				if(!codePostale.isDisplayed()){
					log.debug("Erreur dans l'affichage d'adresse");
				}
				//test sur le numero de contact
				WebElement contact = driver.findElement(By.xpath(xpath+"/div[2]/div[2]/div["+(i+1)+"]/div[2]/span[4]/span[1]"));
				if(!contact.isDisplayed()){
					log.debug("Erreur dans l'affichage d'adresse");
				}
				
			}
			//teste sur le lien toutes les adresse
			WebElement lienFooter = driver.findElement(By.xpath(xpath+"/div[3]/a/span"));
			if(!lienFooter.isDisplayed() || !lienFooter.getText().contains("toutes les adresses")){
				log.debug("Erreur dans l'affichage d'adresse");
			}else{
				switchpage.testerPage(driver, lienFooter, "annuaire.118712.fr", "la page renvoyé par ce lien de resultat n'est pas convenable");
			}
		}
	}

}
