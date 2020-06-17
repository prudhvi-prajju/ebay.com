/**
 * 
 */
package tests;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.EbayHomePage;
import utility.Browsers;

/**
 * @author PRAJJU
 *
 */
public class Runner {

	public RemoteWebDriver driver;
	public Browsers b;
	public WebDriverWait w;
	public EbayHomePage e;

	
	Runner()
	{
		driver = null;
	}
	
	@Parameters({"openBrowser"})
	@BeforeClass
	public void method1(String x) throws Exception
	{		
		b = new Browsers();
		driver = b.openBrowser(x);
		e = new EbayHomePage(driver);
		w = new WebDriverWait(driver,50);
		b.openWebsite("https://in.ebay.com/");
		Thread.sleep(3000);
	}
	
	// to count number of slides in a carousel and calculate slide duration time
	@Test
	public void method2() 
	{
		int count = 0;
		
		for(WebElement slide : e.slides)
		{
			Boolean boo = w.until(ExpectedConditions.attributeToBe(slide, "aria-hidden",""));
			long l1 = System.currentTimeMillis();					
			String val_transform = e.carousel.getCssValue("transform"); // to get value of transform
			if(val_transform.contains("matrix") && boo.equals(true))
			{
				
				count++;
				String trimValue = val_transform.substring(7, val_transform.length()-1);
				String[] value = trimValue.split(",");
				Float x = Float.parseFloat(value[4]); // x-coordinate
				Float y = Float.parseFloat(value[5]); // y-coordinate
				if(x<0)
				{
					System.out.println("Carousel slides are moving from right to left, x= "+x+", y="+y);
				}
				else if(x>0)
				{
					System.out.println("Carousel slides are moving from left to right, x= "+x+", y="+y);
				}
				else
				{
					if(y<0)
					{
						System.out.println("Carousel slides are moving from bottom to top, x= "+x+", y="+y);
					}
					else if(y>0)
					{
						System.out.println("Carousel slides are moving from top to bottom, x= "+x+", y="+y);
					}
					else
					{
						System.out.println("No rotation, x= "+x+", y="+y);
					}
				}
			}
			w.until(ExpectedConditions.attributeToBe(slide, "aria-hidden", "true"));	
			long l2 = System.currentTimeMillis();
			System.out.println("carousel duration time= "+(l2-l1)+" milliseconds");
		}
		System.out.println("the number of slides are :"+count++);
	}
	
	
	
	@AfterMethod
	public void method3() throws Exception
	{
		b.closeOrQuitSite("close");
	}
	
	


}


