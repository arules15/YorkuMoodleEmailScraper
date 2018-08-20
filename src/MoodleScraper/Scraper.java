package MoodleScraper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.omg.CORBA.portable.InputStream;

public class Scraper {
	String emails;
	String moodle;
	String username;
	private String password;

	public String Extraction() throws IOException {
		// get url username, and password from user
		moodle = JOptionPane.showInputDialog("Enter url to moodle");
		username = JOptionPane.showInputDialog("Enter username");
		password = JOptionPane.showInputDialog("Enter pasword");
		
		//Login to moodle
		WebDriver driver = new ChromeDriver();
		driver.get(moodle);
		//enters username
		driver.findElement(By.id("mli")).sendKeys(username);
		//enters password
		driver.findElement(By.id("password")).sendKeys(password);
		//clicks login button
		driver.findElement(By.name("dologin")).click();
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		List<WebElement> d = driver.findElements(By.className("info"));
		for (WebElement x: d) {
			String parts[] = x.getText().split("City/town:");
			String part[] = parts[0].split("Email Address:");
			emails = emails + "\n" +  part[0];
		}
		return emails;
	   
	}

	public static void main(String[] args) throws IOException {
		Scraper a = new Scraper();
		System.out.println(a.Extraction());
	}

}