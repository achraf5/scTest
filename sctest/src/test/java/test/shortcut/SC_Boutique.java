package test.shortcut;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import test.utile.Switch;

public class SC_Boutique {
	Switch switchPage = new Switch();
	Logger log = Logger.getLogger("devpinoyLogger");
	
	
	public SC_Boutique(WebDriver driver, String xpath) throws InterruptedException{
		WebElement sc_boutique = driver.findElement(By.xpath(xpath));
		//test sur la shortcut est ce qu'elle est affich�es
		if(!sc_boutique.isDisplayed()){
			log.debug("sc_boutique n'est pas affichée");
			Assert.fail();
		}else{
			WebElement titre_SC = driver.findElement(By.xpath(xpath+"/div[1]/span"));
			//test sur le titre du shortcut est ce qu'il est bien afich� et egale au nom du SC
			if(!titre_SC.getText().equalsIgnoreCase("BOUTIQUE ORANGE") || !titre_SC.isDisplayed()){
				log.debug("Erreur dans le titre du SC");
				Assert.fail();
			}
			
			//test sur le lien du SC
			WebElement lien_SC = driver.findElement(By.xpath(xpath+"/div[2]/div[1]/a"));
			if(!lien_SC.isDisplayed()){
				log.debug("Erreur dans la lien du SC");
				Assert.fail();
			}else{
				
				switchPage.testerPage(driver, lien_SC, "boutique.orange.fr", "Erreur dans la page renvoyé par le lien");
				
				//test sur l'image du produit
				WebElement img_produit = driver.findElement(By.xpath(xpath+"/div[2]/div[2]/a/img"));
				if(!img_produit.isDisplayed()){
					log.debug("Erreur dans l'image du produit");
					Assert.fail();
				}else{
					switchPage.testerPage(driver, img_produit, "boutique.orange.fr", "Erreur dans le lien renvoyé par l'image du produit");
				}
				
				//test sur le prix
				WebElement prix_produit = driver.findElement(By.xpath(xpath+"/div[2]/div[3]/div[1]"));
				if(!prix_produit.isDisplayed()){
					log.debug("Erreur dans l'affichage du prix");
					Assert.fail();
				}else{
					//a verifier le prix
					System.out.println("prix affich�");
				}
				WebElement notes = driver.findElement(By.xpath(xpath+"/div[2]/div[3]/div[1]/div"));
				if(!notes.isDisplayed() || !notes.getAttribute("class").equalsIgnoreCase("sh_boutique_note")){
					log.debug("Erreur dans l'affichage du notes du produit");
					Assert.fail();
				}
				
				//test sur le bouton voir les offres
				WebElement button = driver.findElement(By.xpath(xpath+"/div[2]/div[3]/div[3]"));
				if(!button.isDisplayed() || !button.getText().equalsIgnoreCase("voir les offres")){
					log.debug("Erreur dans l'affichage du boutton");
					Assert.fail();
				}else{
					switchPage.testerPage(driver, button, "boutique.orange.fr", "Erreur dans le lien renvoyé par le boutton");
				}
				
				//test sur les caracteristiques du produit
				WebElement caracteristique = driver.findElement(By.xpath(xpath+"/div[2]/div[3]/div[2]/span"));
				if(!caracteristique.isDisplayed() || !caracteristique.getText().equalsIgnoreCase("Caractéristiques :")){
					log.debug("Erreur dans l'affichage des caract�ristiques");
					Assert.fail();
				}
				System.out.println(caracteristique.getText());
				//test sur le footer
				WebElement footer = driver.findElement(By.xpath(xpath+"/div[3]/div"));
				if(!footer.isDisplayed() || !footer.getText().equalsIgnoreCase("boutique.orange.fr")){
					log.debug("Erreur dans l'affichage du footer");
					Assert.fail();
				}
			}
		}
	}
}
