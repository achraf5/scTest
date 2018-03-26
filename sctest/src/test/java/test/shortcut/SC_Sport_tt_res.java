package test.shortcut;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import test.utile.Switch;

public class SC_Sport_tt_res {
	Switch switchpage = new Switch();
	Logger log = Logger.getLogger("devpinoyLogger");

	public SC_Sport_tt_res() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SC_Sport_tt_res(WebDriver driver, String xpath) throws InterruptedException {
		// test sur la SC
		WebElement sc_sport_tt_res = driver.findElement(By.xpath(xpath));
		if (!sc_sport_tt_res.isDisplayed()
				|| !sc_sport_tt_res.getAttribute("class").equalsIgnoreCase("resultBlock shortcut shortcutSport")) {
			log.debug("Erreur dans l'affichage du SC SPORT");
			Assert.fail();
		}else{
			// test sur le titre du SC
			WebElement titre = driver.findElement(By.xpath(xpath + "/div[1]"));
			if (!titre.isDisplayed() || !titre.getText().equalsIgnoreCase("SPORT TOUS LES RESULTATS")) {
				log.debug("Erreur dans l'affichage du titre");
				Assert.fail();
			}
			
			// test sur le lien
			WebElement lien = driver.findElement(By.xpath(xpath + "/div[2]/div[1]/a"));
			if (!lien.isDisplayed()) {
				log.debug("Erreur dans l'affichage du lien");
				Assert.fail();
			} else {
				switchpage.testerPage(driver, lien, "sports.orange.fr",
						"la page renvoyé par ce lien n'est pas convenable");
			}
			
			// test sur le logo
			WebElement logo = driver.findElement(By.xpath(xpath + "/div[2]/div[3]/a/div"));
			if (!logo.isDisplayed()) {
				log.debug("Erreur dans l'affichage du logo");
				Assert.fail();
			} else {
				switchpage.testerPage(driver, logo, "sports.orange.fr",
						"la page renvoyé par ce lien n'est pas convenable");
			}
			
			//test sur le bloc d'info sur les rencontre
			List<WebElement> infos = driver.findElements(By.xpath(xpath+"/div[2]/div[4]/div"));
			for(int i=0;i<infos.size();i++){
				if(infos.get(i).getAttribute("class").contains("sportBlock")){
					if(!infos.get(i).isDisplayed()){
						log.debug("Erreur dans l'affichage d'infos");
						Assert.fail();
					}else{
						//test sur match retour
						if(i==0){
							if(!infos.get(i).getText().contains("Match retour")){
								log.debug("Erreur dans l'affichage du match retour");
								Assert.fail();
							}
						}
						//test sur resultat match retour
						if(i==2){
							WebElement matchRetour = driver.findElement(By.xpath(xpath+"/div[2]/div[4]/div["+(i+1)+"]/a"));
							switchpage.testerPage(driver, matchRetour, "sports.orange.fr", "la page renvoy� par ce lien n'est pas convenable");
						}
						
						//test sur le match allez
						if(i==3){
							if(!infos.get(i).getText().contains("Match aller")){
								log.debug("Erreur dans l'affichage du match aller");
								Assert.fail();
							}
						}
						//test sur resultat match aller
						if(i==5){
							WebElement matchRetour = driver.findElement(By.xpath(xpath+"/div[2]/div[4]/div["+(i+1)+"]/a"));
							switchpage.testerPage(driver, matchRetour, "sports.orange.fr", "la page renvoyé par ce lien n'est pas convenable");
						}
					}
				}
			}
			
			//test sur le footer homepage
			WebElement homepage = driver.findElement(By.xpath(xpath + "/div[2]/div[5]"));
			if (!homepage.isDisplayed()) {
				log.debug("Erreur dans l'affichage du lien homepage");
				Assert.fail();
			}
		}
		
	}
	
	

}
