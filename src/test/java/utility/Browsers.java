package utility;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browsers {

public RemoteWebDriver driver;
private ArrayList<String> l;
private Set<String> s;
	
	public Browsers()
	{
		driver=null;
	}
	
	public RemoteWebDriver openBrowser(String bn)
	{
		switch(bn)
		{
		case "chrome":  WebDriverManager.chromedriver().setup();
						System.setProperty("webdriver.chrome.silentOutput", "true");
						driver = new ChromeDriver();
						break;
						
		case "firefox": WebDriverManager.firefoxdriver().setup();
						driver = new FirefoxDriver();
						break;
						
		default: System.out.println("no browser");
		}
		driver.manage().window().maximize();
		return driver;
	}
	
	public void openWebsite(String x)
	{
		driver.get(x);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}	
	
	
	public void closeOrQuitSite(String x)
	{
		
		switch(x)
		{
		case "close": driver.close();
		break;
		
		case "quit": driver.quit();
		break;
		}
	}
	
	
	
	
}
