package test.sctest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import test.shortcut.SC_Album;
import test.shortcut.SC_Boutique;
import test.shortcut.SC_ChaineTV;
import test.shortcut.SC_Cine;
import test.shortcut.SC_Club;
import test.shortcut.SC_GuideTV;
import test.shortcut.SC_Jeux;
import test.shortcut.SC_Meteo;
import test.shortcut.SC_Music;
import test.shortcut.SC_Renseignement;
import test.shortcut.SC_Sport;
import test.shortcut.SC_Sport_tt_res;
import test.shortcut.SC_TOP14;
import test.shortcut.SC_Tendance;
import test.shortcut.SC_VOD;
import test.utile.ReadExcel;

public class AppTest{
	public WebDriver driver;
	ReadExcel url = new ReadExcel();
	String nameFile = "Test_shortcut.xlsx";
	String sheet = "Feuil1";
	String path = "C:\\Users\\abousselhami\\workspace\\sctest\\src\\main\\resources\\Test_Shortcut.xlsx";
	
	SC_Boutique sc_boutique;
	SC_ChaineTV sc_tv;
	SC_GuideTV sc_guideTv;
	SC_Jeux sc_jeux;
	SC_Cine sc_cine;
	SC_VOD sc_vod;
	SC_Tendance sc_tendance;
	SC_Sport sc_sport;
	SC_TOP14 sc_top;
	SC_Club sc_club;
	SC_Sport_tt_res sc_sport_tt_res;
	SC_Renseignement sc_renseignement;
	SC_Meteo sc_meteo;
	SC_Album sc_album;
	SC_Music sc_music;
	
	@Test(enabled=true)
	public void Test_SC_Boutique(){
		try {
			String urltest = url.readFromExcel(nameFile, sheet, path, 0, 1);
			driver.get(urltest);
			sc_boutique = new SC_Boutique(driver,"//*[@id='resultId']/div[5]/div");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(enabled=false)
	void Test_SC_ChaineTV() throws InterruptedException{
		try {
			String urltest = url.readFromExcel(nameFile, sheet, path, 14, 1);
			driver.get(urltest);
			sc_tv = new SC_ChaineTV(driver, "//*[@id='resultId']/div[5]/div");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(enabled=false) 
	void Test_SC_Jeux(){
		String urltest;
		try {
			urltest = url.readFromExcel(nameFile, sheet, path, 2, 1);
			driver.get(urltest);
			sc_jeux = new SC_Jeux(driver, "//*[@id='resultId']/div[5]/div");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test(enabled=false)
	void Test_SC_GuideTV(){
		try {
			String urltest = url.readFromExcel(nameFile, sheet, path, 1, 1);
			driver.get(urltest);
			sc_guideTv = new SC_GuideTV(driver, "//*[@id='resultId']/div[4]/div");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(enabled=false)
	void Test_SC_Film(){
		try {
			String urltest = url.readFromExcel(nameFile, sheet, path, 3, 1);
			driver.get(urltest);
			sc_cine = new SC_Cine(driver, "//*[@id='resultId']/div[5]/div");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(enabled=false)
	void Test_SC_VOD(){
		try {
			String urltest = url.readFromExcel(nameFile, sheet, path, 4, 1);
			driver.get(urltest);
			sc_vod = new SC_VOD(driver, "//*[@id='resultId']/div[5]/div");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(enabled=false) 
	void Test_SC_Tendance(){
		try {
			String urltest = url.readFromExcel(nameFile, sheet, path, 5, 1);
			driver.get(urltest);
			sc_tendance = new SC_Tendance(driver, "//*[@id='resultId']/div[5]/div");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(enabled=false)
	void Test_SC_Top14(){
		try {
			String urltest = url.readFromExcel(nameFile, sheet, path, 6, 1);
			driver.get(urltest);
			sc_top = new SC_TOP14(driver,"//*[@id='resultId']/div[5]/div" );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(enabled=false)
	void Test_SC_Club(){
		try {
			String urltest = url.readFromExcel(nameFile, sheet, path, 7, 1);
			driver.get(urltest);
			sc_club = new SC_Club(driver, "//*[@id='resultId']/div[5]/div");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(enabled=false)
	void Test_SC_Sport(){
		try {
			String urltest = url.readFromExcel(nameFile, sheet, path, 8, 1);
			driver.get(urltest);
			sc_sport = new SC_Sport(driver, "//*[@id='resultId']/div[5]/div");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(enabled=false)
	void Test_SC_Sport_Tt_Res(){
		try {
			String urltest = url.readFromExcel(nameFile, sheet, path, 9, 1);
			driver.get(urltest);
			sc_sport_tt_res = new SC_Sport_tt_res(driver, "//*[@id='resultId']/div[5]/div");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(enabled=false)
	void Test_SC_Renseignement(){
		try {
			String urltest = url.readFromExcel(nameFile, sheet, path, 10, 1);
			driver.get(urltest);
			sc_renseignement = new SC_Renseignement(driver, "//*[@id='resultId']/div[5]/div");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(enabled=false)
	void Test_SC_Meteo(){
		try {
			String urltest = url.readFromExcel(nameFile, sheet, path, 11, 1);
			driver.get(urltest);
			sc_meteo = new SC_Meteo(driver, "//*[@id='resultId']/div[5]/div");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(enabled=false)
	void Test_SC_Album(){
		try {
			String urltest = url.readFromExcel(nameFile, sheet, path, 12, 1);
			driver.get(urltest);
			sc_album = new SC_Album(driver, "//*[@id='resultId']/div[5]/div");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(enabled=false)
	void Test_SC_Music(){
		try {
			String urltest = url.readFromExcel(nameFile, sheet, path, 13, 1);
			driver.get(urltest);
			sc_music = new SC_Music(driver, "//*[@id='resultId']/div[5]/div");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@BeforeMethod
	public void beforeMethod(){
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	
	@AfterMethod
	public void afterMethod(){
		driver.close();
	}
    
}
