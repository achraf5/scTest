package test.shortcut;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import test.utile.Switch;

public class SC_Album {
	Switch switchpage = new Switch();
	Logger log = Logger.getLogger("devpinoyLogger");

	public SC_Album() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SC_Album(WebDriver driver, String xpath) throws InterruptedException {
		// test sur la SC
		WebElement sc_Album = driver.findElement(By.xpath(xpath));
		if (!sc_Album.isDisplayed()
				|| !sc_Album.getAttribute("class").equalsIgnoreCase("resultBlock shortcut shortcutBdc")) {
			log.debug("Erreur dans l'affichage du SC ALBUM A ECOUTER AVEC DEEZER");
			Assert.fail();
		} else {
			// test sur le titre du SC
			WebElement titre = driver.findElement(By.xpath(xpath + "/div[1]/div[1]"));
			if (!titre.isDisplayed() || !titre.getText().equalsIgnoreCase("ALBUM A ECOUTER AVEC DEEZER")) {
				log.debug("Erreur dans l'affichage du titre");
				Assert.fail();
			}
			
			// test sur le lien du Album
			WebElement lien_album = driver.findElement(By.xpath(xpath + "/div[2]/a"));
			if (!lien_album.isDisplayed()) {
				log.debug("Erreur dans l'affichage du lien du personne");
				Assert.fail();
			} else {
				switchpage.testerPage(driver, lien_album, "orange.deezer.com", "la page renvoyé par ce lien n'est pas convenable");
			}
			
			// test sur l'image du Album
			WebElement img_album = driver.findElement(By.xpath(xpath + "/div[3]/div[1]/a/img"));
			if (!img_album.isDisplayed()) {
				log.debug("Erreur dans l'affichage d'image du ALbum");
				Assert.fail();
			} else {
				switchpage.testerPage(driver, img_album, "orange.deezer.com", "la page renvoyé par cette image n'est pas convenable");
			}
			
			//test sur l'artiste
			WebElement artiste = driver.findElement(By.xpath(xpath + "/div[3]/div[2]/div/span[1]/span[1]"));
			if (!artiste.isDisplayed()) {
				log.debug("Erreur dans l'affichage d'Artiste");
				Assert.fail();
			}
			
			//test sur le resultas sur l'artiste
			WebElement lienArtiste = driver.findElement(By.xpath(xpath + "/div[3]/div[2]/div/span[1]/span[2]"));
			if (!lienArtiste.isDisplayed()) {
				log.debug("Erreur dans l'affichage du lien de l'artiste");
				Assert.fail();
			}else{
				lienArtiste.click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				String URL = driver.getCurrentUrl();
				if (!URL.contains("lemoteur.orange.fr")) {
					log.debug("le lien orange n'envoie pas a la page le moteur");
					Assert.fail();
				} else {
					driver.navigate().back();
				}
			}
			
			//test sur la date de sortie
			WebElement dateSortie = driver.findElement(By.xpath(xpath + "/div[3]/div[2]/div/span[2]"));
			if (!dateSortie.isDisplayed()) {
				log.debug("Erreur dans l'affichage du date de sortie");
				Assert.fail();
			}
			
			//test sur le genre
			WebElement genre = driver.findElement(By.xpath(xpath + "/div[3]/div[2]/div/span[3]"));
			if (!genre.isDisplayed()) {
				log.debug("Erreur dans l'affichage du genre");
				Assert.fail();
			}
			
			//test sur le nombre de titre
			WebElement nbrTitre = driver.findElement(By.xpath(xpath + "/div[3]/div[2]/div/span[4]"));
			if (!nbrTitre.isDisplayed()) {
				log.debug("Erreur dans l'affichage du date de sortie");
				Assert.fail();
			}
			
			//test sur le nombre de titre
			WebElement duree = driver.findElement(By.xpath(xpath + "/div[3]/div[2]/div/span[5]"));
			if (!duree.isDisplayed()) {
				log.debug("Erreur dans l'affichage du date de sortie");
				Assert.fail();
			}
			
			// test sur la liste des liens
			List<WebElement> listLinks = driver.findElements(By.xpath(xpath + "/div[4]/div[1]/ul/li"));
			for (int i = 0; i < listLinks.size(); i++) {

				if (i == 0) {
					WebElement lien = driver.findElement(By.xpath(xpath + "/div[4]/div[1]/ul/li[" + (i + 1) + "]"));
					if (!lien.isDisplayed() || !lien.getText().equalsIgnoreCase("titres :")) {
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
