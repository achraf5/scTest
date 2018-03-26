package test.shortcut;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import test.utile.Switch;

public class SC_GuideTV {
	Switch switchpage = new Switch();
	Logger log = Logger.getLogger("devpinoyLogger");
	Date aujourdhui = new Date(); 

	DateFormat fullDateFormat = DateFormat.getDateTimeInstance( DateFormat.FULL, DateFormat.FULL);
	String dateAuj = fullDateFormat.format(aujourdhui);
	public SC_GuideTV(){
		
	}
	
	public SC_GuideTV(WebDriver driver, String xpath) throws InterruptedException{
		//test sur la shortcut
		WebElement sc_GuideTv = driver.findElement(By.xpath(xpath));
		if(!sc_GuideTv.isDisplayed() || !sc_GuideTv.getAttribute("class").equalsIgnoreCase("resultBlock shortcut shortcutGuideTv")){
			System.err.println("Erreur dans l'affichage du SC Guide tv");
			Assert.fail();
		}else{
			//test sur le titre du SC
			WebElement titre = driver.findElement(By.xpath(xpath+"/div[1]/div[1]"));
			if(!titre.isDisplayed() || !titre.getText().equalsIgnoreCase("PROGRAMME TV")){
				System.err.println("Erreur dans l'affichage du titre");
				Assert.fail();
			}
			
			//test sur la date
			WebElement date = driver.findElement(By.xpath(xpath+"/div[1]/div[2]"));
			
			if(!dateAuj.contains(date.getText())){
				System.err.println("Erreur dans la date");
				Assert.fail();
			}else {
				System.out.println("2");
			}
			
			//test sur le lien toute la programmation t�l�
			WebElement lienTtProgTele = driver.findElement(By.xpath(xpath+"/a"));
			if(!lienTtProgTele.isDisplayed() || !lienTtProgTele.getText().equalsIgnoreCase("toute la programmation télé")){
				System.out.println("le lien toute la programmation télé n'est pas affich�");
				Assert.fail();
			}
			else{
				System.out.println("3");
			}
			
			//on trouve dans l'affichage des channels dans cette SC 9 chaines divis� sur 3 bloc verticalement
			//On va tester sur chaque bloc
			List<WebElement> bloc = driver.findElements(By.xpath(xpath+"/div[2]/div"));
			
			for(int i=0;i<bloc.size();i++){
				//on va tester sur les channels
				List<WebElement> chaines = driver.findElements(By.xpath(xpath+"/div[2]/div["+(i+1)+"]/table"));
				for(int j=0;j<chaines.size();j++){
					//test sur le logo du channel
					WebElement logoCH = driver.findElement(By.xpath(xpath+"/div[2]/div["+(i+1)+"]/table["+(j+1)+"]/tbody/tr[1]/td[1]/a/img"));
					if(!logoCH.isDisplayed()){
						System.err.println("l'image du channel n'est pas affiché");
						Assert.fail();
					}else{
						switchpage.testerPage(driver, logoCH, "programme-tv.orange.fr", "la page renvoyé par ce lien n'est pas convenable au niveau du test "+i+","+j);
					}
					
					//test sur l'horaire de l'emission
					WebElement horaire = driver.findElement(By.xpath(xpath+"/div[2]/div["+(i+1)+"]/table["+(j+1)+"]/tbody/tr[1]/td[2]/span"));
					if(!horaire.isDisplayed()){
						System.err.println("l'horaire n'est pas affich�");
						Assert.fail();
					}
					
					//test sur le nom du programme
					WebElement nom_prog = driver.findElement(By.xpath(xpath+"/div[2]/div["+(i+1)+"]/table["+(j+1)+"]/tbody/tr[1]/td[3]/a"));
					if(!nom_prog.isDisplayed()){
						System.err.println("le nom du programme n'est pas affich�");
						Assert.fail();
					}else{
						switchpage.testerPage(driver, nom_prog, "programme-tv.orange.fr", "la page renvoyé par ce lien n'est pas convenable au niveau du test "+i+","+j);
					}
				}
			}
			
			
		}
	}
}
