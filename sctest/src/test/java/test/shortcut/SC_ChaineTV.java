package test.shortcut;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import test.utile.Switch;

public class SC_ChaineTV {
	Switch switchpage = new Switch();
	Logger log = Logger.getLogger("devpinoyLogger");
	
	public SC_ChaineTV(){
		
	}
	
	public SC_ChaineTV(WebDriver driver, String xpath) throws InterruptedException{
		WebElement sc_prog_tv = driver.findElement(By.xpath(xpath));
		if(!sc_prog_tv.isDisplayed()){
			log.debug("Erreur dans l'affichage du SC Programme tv");
			Assert.fail();
		}else{
			//test sur le titre du SC
			WebElement titre = driver.findElement(By.xpath(xpath+"/div[1]"));
			if(!titre.isDisplayed() || !titre.getText().equalsIgnoreCase("PROGRAMME TV")){
				log.debug("Erreur dans l'affichage du titre");
				Assert.fail();
			}
			
			//test sur l'affichage des programme
			List<WebElement> contentTvChannel = driver.findElements(By.xpath(xpath+"/div[2]/ul/li/a"));
			
			//verification du nombre de resultats (le nombre de resultats doit etre superieur strictement
			//� 0 est inferieur ou egale 3
			if(contentTvChannel.size() < 0 || contentTvChannel.size() >= 4){
				log.debug("le nombre de resultats programme tv n'est pas compatible � la condition");
				Assert.fail();
			}
			for(int i=0;i<contentTvChannel.size();i++){
				WebElement img = driver.findElement(By.xpath(xpath+"/div[2]/ul/li["+(i+1)+"]/a/div[1]/img"));
				WebElement lien = driver.findElement(By.xpath(xpath+"/div[2]/ul/li["+(i+1)+"]/a/div[2]"));
				if(i==(contentTvChannel.size()-1)){
					switchpage.testerPage(driver, contentTvChannel.get(i), "replay.orange.fr", "Erreur dans la page renvoyé par le lien");
					//test sur l'image
					if(!img.isDisplayed()){
						log.debug("l'image n'est pas affiché");
						Assert.fail();
					}
					
					//test sur le lien
					if(!lien.isDisplayed()){
						log.debug("le lien n'est pas affiché");
						Assert.fail();
					}
				}else{
					switchpage.testerPage(driver, contentTvChannel.get(i), "programme-tv.orange.fr", "Erreur dans la page renvoyé par le lien"+i);
					//test sur l'image
					if(!img.isDisplayed()){
						log.debug("l'image n'est pas affiché");
						Assert.fail();
					}
					//test sur le lien
					if(!lien.isDisplayed()){
						log.debug("le lien n'est pas affiché");
						Assert.fail();
					}
				}
				
				//test sur le lien toute la programation t�l�
				WebElement lienFooter = driver.findElement(By.xpath(xpath+"/div[3]/a"));
				if(!lienFooter.isDisplayed()){
					log.debug("le lien n'est pas affiché");
					Assert.fail();
				}else{
					switchpage.testerPage(driver, lienFooter, "programme-tv.orange.fr", "le lien toute la programation télé nous ne ronvoie pas vers la bonne page");
				}
			}
			
			
			
		}
	}

}
