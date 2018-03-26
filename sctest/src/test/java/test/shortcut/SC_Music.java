package test.shortcut;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import test.utile.Switch;

public class SC_Music {
	Switch switchpage = new Switch();
	Logger log = Logger.getLogger("devpinoyLogger");

	public SC_Music() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SC_Music(WebDriver driver, String xpath) throws InterruptedException {
		// test sur la SC
		WebElement sc_Album = driver.findElement(By.xpath(xpath));
		if (!sc_Album.isDisplayed()
				|| !sc_Album.getAttribute("class").equalsIgnoreCase("resultBlock shortcut shortcutBdc")) {
			log.debug("Erreur dans l'affichage du SC ECOUTER AVEC DEEZER");
			Assert.fail();
		}else{
			// test sur le titre du SC
			WebElement titre = driver.findElement(By.xpath(xpath + "/div[1]/div[1]"));
			if (!titre.isDisplayed() || !titre.getText().equalsIgnoreCase("ECOUTER AVEC DEEZER")) {
				log.debug("Erreur dans l'affichage du titre");
				Assert.fail();
			}
			
			// test sur le lien du Music
			WebElement lien_music = driver.findElement(By.xpath(xpath + "/div[2]/a"));
			if (!lien_music.isDisplayed()) {
				log.debug("Erreur dans l'affichage du lien du personne");
				Assert.fail();
			}else{
				switchpage.testerPage(driver, lien_music, "orange.deezer.com", "la page renvoyé par ce lien n'est pas convenable");
			}
			
			// test sur l'image du Album
			WebElement img_Music = driver.findElement(By.xpath(xpath + "/div[3]/div[1]/a/img"));
			if (!img_Music.isDisplayed()) {
				log.debug("Erreur dans l'affichage d'image du ALbum");
				Assert.fail();
			} else {
				switchpage.testerPage(driver, img_Music, "orange.deezer.com",
						"la page renvoy� par cette image n'est pas convenable");
			}
			
			//test sur l'artiste
			WebElement artiste = driver.findElement(By.xpath(xpath + "/div[3]/div[2]/div/span[1]"));
			if (!artiste.isDisplayed()) {
				log.debug("Erreur dans l'affichage d'Artiste");
				Assert.fail();
			}
			
			//test sur l'album
			WebElement album = driver.findElement(By.xpath(xpath + "/div[3]/div[2]/div/span[2]/span[1]"));
			if (!album.isDisplayed()) {
				log.debug("Erreur dans l'affichage d'Artiste");
				Assert.fail();
			}
			
			//test sur le lien de l'album
			WebElement lien_album = driver.findElement(By.xpath(xpath + "/div[3]/div[2]/div/span[2]/span[2]/a"));
			if (!lien_album.isDisplayed()) {
				log.debug("Erreur dans l'affichage d'Artiste");
				Assert.fail();
			}else{
				lien_album.click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				String URL = driver.getCurrentUrl();
				if (!URL.contains("lemoteur.orange.fr")) {
					log.debug("le lien orange n'envoie pas a la page le moteur");
					Assert.fail();
				} else {
					driver.navigate().back();
				}
			}
			
			//test sur le genre
			WebElement genre = driver.findElement(By.xpath(xpath + "/div[3]/div[2]/div/span[3]"));
			if (!genre.isDisplayed()) {
				log.debug("Erreur dans l'affichage du genre");
				Assert.fail();
			}
			
			//test sur la duree
			WebElement duree = driver.findElement(By.xpath(xpath + "/div[3]/div[2]/div/span[4]"));
			if (!duree.isDisplayed()) {
				log.debug("Erreur dans l'affichage de la duree");
				Assert.fail();
			}
			
			// test sur la liste des liens
			List<WebElement> listLinks = driver.findElements(By.xpath(xpath + "/div[4]/div[1]/ul/li"));
			for (int i = 0; i < listLinks.size(); i++) {

				if (i == 0) {
					WebElement lien = driver.findElement(By.xpath(xpath + "/div[4]/div[1]/ul/li[" + (i + 1) + "]"));
					if (!lien.isDisplayed() || !lien.getText().equalsIgnoreCase("autres titres :")) {
						log.debug("Erreur dans l'affichage de titres");
						Assert.fail();
					}
				} else {
					WebElement lien = driver
							.findElement(By.xpath(xpath + "/div[4]/div[1]/ul/li[" + (i + 1) + "]/span/a"));
					lien.click();
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					String URL = driver.getCurrentUrl();
					if (!URL.contains("lemoteur.orange.fr")) {
						log.debug("le lien orange n'envoie pas a la page le moteur");
						Assert.fail();
					} else {
						driver.navigate().back();
					}
				}
			}
		}
	}
}
