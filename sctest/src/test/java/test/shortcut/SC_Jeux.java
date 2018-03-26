package test.shortcut;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import test.utile.Switch;

public class SC_Jeux {
	Switch switchpage = new Switch();
	Logger log = Logger.getLogger("devpinoyLogger");

	public SC_Jeux() {
		super();
	}
	
	public SC_Jeux(WebDriver driver, String xpath) throws InterruptedException{
		//test sur la SC
		WebElement sc_jeux = driver.findElement(By.xpath(xpath));
		if(!sc_jeux.isDisplayed() || !sc_jeux.getAttribute("class").equalsIgnoreCase("resultBlock shortcut shortcutBdc")){
			log.debug("Erreur dans l'affichage du SC JEUX");
			Assert.fail();
		}else{
			//test sur le titre du SC
			WebElement titre = driver.findElement(By.xpath(xpath+"/div[1]/div[1]"));
			if(!titre.isDisplayed() || !titre.getText().equalsIgnoreCase("JEUX")){
				log.debug("Erreur dans l'affichage du titre");
				Assert.fail();
			}
			
			//test sur le lien du Jeu
			WebElement lien_jeu = driver.findElement(By.xpath(xpath+"/div[2]/a"));
			if(!lien_jeu.isDisplayed()){
				log.debug("Erreur dans l'affichage du lien du jeu");
				Assert.fail();
			}else{
				switchpage.testerPage(driver, lien_jeu, "jeuxvideo.jeu.orange.fr", "la page renvoyé par ce lien n'est pas convenable");
			}
			
			//test sur l'image du jeu
			WebElement img_jeu = driver.findElement(By.xpath(xpath+"/div[3]/div[1]/a/img"));
			if(!img_jeu.isDisplayed()){
				log.debug("Erreur dans l'affichage d'image du jeu");
				Assert.fail();
			}else{
				switchpage.testerPage(driver, img_jeu, "jeuxvideo.jeu.orange.fr", "la page renvoyé par cette image n'est pas convenable");
			}
			
			//test sur la description
			WebElement description = driver.findElement(By.xpath(xpath+"/div[3]/div[2]/span[1]"));
			if(!description.isDisplayed()){
				log.debug("Erreur dans l'affichage de la description du jeu");
				Assert.fail();
			}
			
			//test sur les etoile
			WebElement etoile = driver.findElement(By.xpath(xpath+"/div[3]/div[2]/div[1]"));
			if(!etoile.isDisplayed()){
				log.debug("Erreur dans l'affichage des etoiles du jeu");
				Assert.fail();
			}
			
			//test sur le bouton jouer
			WebElement button = driver.findElement(By.xpath(xpath+"/div[3]/div[2]/div[2]/a"));
			if(!button.isDisplayed() || !button.getText().equalsIgnoreCase("jouer")){
				log.debug("Erreur dans l'affichage du boutton jouer");
				Assert.fail();
			}else{
				switchpage.testerPage(driver, button, "jeuxvideo.jeu.orange.fr", "la page renvoy� par ce bouton n'est pas convenable");
			}
			
			//test sur la liste des liens
			List<WebElement> listLinks = driver.findElements(By.xpath(xpath+"/div[4]/div[1]/ul/li"));
			System.out.println("sizeeeee "+listLinks.size());
			for(int i=0;i<listLinks.size();i++){
				
				if(i==0){
					WebElement lien = driver.findElement(By.xpath(xpath+"/div[4]/div[1]/ul/li["+(i+1)+"]"));
					if(!lien.isDisplayed() || !lien.getText().equalsIgnoreCase("voir aussi :")){
						log.debug("Erreur dans l'affichage de voir aussi");
						Assert.fail();
					}
				}else{
					WebElement lien = driver.findElement(By.xpath(xpath+"/div[4]/div[1]/ul/li["+(i+1)+"]/span/a"));
					switchpage.testerPage(driver, lien, "jeuxvideo.jeu.orange.fr", "la page renvoyé par ce bouton n'est pas convenable");
				}
			}
			
		}
	}
	

}
