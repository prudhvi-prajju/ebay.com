/**
 * 
 */
package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author PRAJJU
 *
 */
public class EbayHomePage {

	
public RemoteWebDriver driver;
	
	
	public EbayHomePage(RemoteWebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//*[@class='carousel__list'])[1]") public WebElement carousel; // carousel slides
	
	@FindBy(xpath = "(//*[@class='carousel__list'])[1]/li") public List<WebElement> slides;  
	
	public void openWebsite(String x)
	{
		driver.get(x);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	
}
