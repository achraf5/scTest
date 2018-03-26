package test.shortcut;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import test.utile.Switch;

public class SC_Tendance {
	Switch switchpage = new Switch();
	Logger log = Logger.getLogger("devpinoyLogger");

	public SC_Tendance() {
		super();
	}

	public SC_Tendance(WebDriver driver, String xpath) throws InterruptedException {
		// test sur la SC
		WebElement sc_Tendance = driver.findElement(By.xpath(xpath));
		if (!sc_Tendance.isDisplayed()
				|| !sc_Tendance.getAttribute("class").equalsIgnoreCase("resultBlock shortcut shortcutBdc")) {
			log.debug("Erreur dans l'affichage du SC PEOPLE AVEC ORANGE TENDANCES");
			Assert.fail();
		} else {
			// test sur le titre du SC
			WebElement titre = driver.findElement(By.xpath(xpath + "/div[1]/div[1]"));
			if (!titre.isDisplayed() || !titre.getText().equalsIgnoreCase("PEOPLE AVEC ORANGE TENDANCES")) {
				log.debug("Erreur dans l'affichage du titre");
				Assert.fail();
			}

			// test sur le lien du Film
			WebElement lien_personne = driver.findElement(By.xpath(xpath + "/div[2]/a"));
			if (!lien_personne.isDisplayed()) {
				log.debug("Erreur dans l'affichage du lien du personne");
				Assert.fail();
			} else {
				switchpage.testerPage(driver, lien_personne, "people.orange.fr",
						"la page renvoyé par ce lien n'est pas convenable");
			}

			// test sur l'image du jeu
			WebElement img_personne = driver.findElement(By.xpath(xpath + "/div[3]/div[1]/a/img"));
			if (!img_personne.isDisplayed()) {
				log.debug("Erreur dans l'affichage d'image du personne");
				Assert.fail();
			} else {
				switchpage.testerPage(driver, img_personne, "people.orange.fr",
						"la page renvoy� par cette image n'est pas convenable");
			}

			// test sur la description
			WebElement description = driver.findElement(By.xpath(xpath + "/div[3]/div[2]/span[1]"));
			if (!description.isDisplayed()) {
				log.debug("Erreur dans l'affichage de la description du jeu");
				Assert.fail();
			}

			// test sur la date de sortie
			WebElement profession = driver.findElement(By.xpath(xpath + "/div[3]/div[2]/div/span[1]/span[1]"));
			if (!profession.isDisplayed() || !profession.getText().equalsIgnoreCase("profession :")) {
				log.debug("Erreur dans l'affichage du profession");
				Assert.fail();
			}

			// test sur le genre
			WebElement naissance = driver.findElement(By.xpath(xpath + "/div[3]/div[2]/div/span[2]/span[1]"));
			if (!naissance.isDisplayed() || !naissance.getText().equalsIgnoreCase("naissance :")) {
				log.debug("Erreur dans l'affichage du naissance");
				Assert.fail();
			}

			// test sur le r�alisateur
			WebElement nomNaissance = driver.findElement(By.xpath(xpath + "/div[3]/div[2]/div/span[3]/span[1]"));
			if (!nomNaissance.isDisplayed() || !nomNaissance.getText().equalsIgnoreCase("nom de naissance :")) {
				log.debug("Erreur dans l'affichage du nom naissance");
				Assert.fail();
			}


			// test sur la liste des liens
			List<WebElement> listLinks = driver.findElements(By.xpath(xpath + "/div[4]/div[1]/ul/li"));
			for (int i = 0; i < listLinks.size(); i++) {

				if (i == 0) {
					WebElement lien = driver.findElement(By.xpath(xpath + "/div[4]/div[1]/ul/li[" + (i + 1) + "]"));
					if (!lien.isDisplayed() || !lien.getText().equalsIgnoreCase("film :")) {
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
