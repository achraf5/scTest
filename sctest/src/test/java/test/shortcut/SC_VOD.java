package test.shortcut;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import test.utile.Switch;

public class SC_VOD {
	Switch switchpage = new Switch();
	Logger log = Logger.getLogger("devpinoyLogger");

	public SC_VOD() {
		super();
	}
	
	public SC_VOD(WebDriver driver, String xpath) throws InterruptedException{
		// test sur la SC
		WebElement sc_Cine = driver.findElement(By.xpath(xpath));
		if (!sc_Cine.isDisplayed()
				|| !sc_Cine.getAttribute("class").equalsIgnoreCase("resultBlock shortcut shortcutBdc")) {
			log.debug("Erreur dans l'affichage du VIDEO A LA DEMANDE D'ORANGE");
			Assert.fail();
		} else {
			// test sur le titre du SC
			WebElement titre = driver.findElement(By.xpath(xpath + "/div[1]/div[1]"));
			if (!titre.isDisplayed() || !titre.getText().equalsIgnoreCase("VIDEO A LA DEMANDE D'ORANGE")) {
				log.debug("Erreur dans l'affichage du titre");
				Assert.fail();
			}

			// test sur le lien du Film
			WebElement lien_video = driver.findElement(By.xpath(xpath + "/div[2]/a"));
			if (!lien_video.isDisplayed()) {
				log.debug("Erreur dans l'affichage du lien du film");
				Assert.fail();
			} else {
				switchpage.testerPage(driver, lien_video, "video-a-la-demande.orange.fr","la page renvoyé par ce lien n'est pas convenable");
			}

			// test sur l'image du jeu
			WebElement img_video = driver.findElement(By.xpath(xpath + "/div[3]/div[1]/a/img"));
			if (!img_video.isDisplayed()) {
				log.debug("Erreur dans l'affichage d'image du film");
				Assert.fail();
			} else {
				switchpage.testerPage(driver, img_video, "video-a-la-demande.orange.fr",
						"la page renvoy� par cette image n'est pas convenable");
			}

			// test sur la description
			WebElement description = driver.findElement(By.xpath(xpath + "/div[3]/div[2]/span[1]"));
			if (!description.isDisplayed()) {
				log.debug("Erreur dans l'affichage de la description du jeu");
				Assert.fail();
			}

			// test sur la date de sortie
			WebElement anneeSortie = driver.findElement(By.xpath(xpath + "/div[3]/div[2]/div/span[1]/span[1]"));
			if (!anneeSortie.isDisplayed() || !anneeSortie.getText().equalsIgnoreCase("année de sortie :")) {
				log.debug("Erreur dans l'affichage du date de sortie");
				Assert.fail();
			}

			// test sur le genre
			WebElement duree = driver.findElement(By.xpath(xpath + "/div[3]/div[2]/div/span[2]/span[1]"));
			if (!duree.isDisplayed() || !duree.getText().equalsIgnoreCase("durée :")) {
				log.debug("Erreur dans l'affichage du duree");
				Assert.fail();
			}

			// test sur le r�alisateur
			WebElement realisateur = driver.findElement(By.xpath(xpath + "/div[3]/div[2]/div/span[3]/span[1]"));
			if (!realisateur.isDisplayed() || !realisateur.getText().equalsIgnoreCase("réalisé par :")) {
				log.debug("Erreur dans l'affichage du realisateur");
				Assert.fail();
			}
			WebElement nomRealisateur = driver.findElement(By.xpath(xpath + "/div[3]/div[2]/div/span[3]/span[2]/a"));
			if (!nomRealisateur.isDisplayed()) {
				log.debug("Erreur dans l'affichage du nom realisateur");
				Assert.fail();
			} else {
				nomRealisateur.click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				String URL = driver.getCurrentUrl();
				if (!URL.contains("lemoteur.orange.fr")) {
					log.debug("le lien orange n'envoie pas a la page le moteur");
					Assert.fail();
				} else {
					driver.navigate().back();
				}
			}

			// test sur la liste des liens
			List<WebElement> listLinks = driver.findElements(By.xpath(xpath + "/div[4]/div[1]/ul/li"));
			for (int i = 0; i < listLinks.size(); i++) {

				if (i == 0) {
					WebElement lien = driver.findElement(By.xpath(xpath + "/div[4]/div[1]/ul/li[" + (i + 1) + "]"));
					if (!lien.isDisplayed() || !lien.getText().equalsIgnoreCase("avec :")) {
						log.debug("Erreur dans l'affichage de voir aussi");
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
