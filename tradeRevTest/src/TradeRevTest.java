import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Reporter;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

/* Scenario 1: check whether Canada TradeRev career page is displayed properly             
1.     Visit https://www.traderev.com/en-ca/
2.     Navigate to “Careers” page
3.     The Careers page should be displayed properly
4.     Click on “Canadian Opportunities”
5.     Validate whether Canadian job site is displayed properly
 
Scenario 2: check whether job filter (city) is working properly      
1.      Visit https://jobs.lever.co/traderev
2.      Filter the Search results by City “Toronto, Ontario, Canada”
3.      All the job results displayed should belong to “Toronto, Ontario, Canada” validate this.
 
Scenario 3: check whether job filter (city) and (team) is working properly   
1.      Visit https://jobs.lever.co/traderev
2.      Filter the Search results by City “Toronto, Ontario, Canada” and Team “Engineering”
3.      All the job results displayed should belong to region “Toronto, Ontario, Canada” and “Engineering” team, prove this.
4.     Finally log the total available positions listed. */ 

public class TradeRevTest {
    private WebDriver driver;
    private String parentWindow;
    private Set<String> allwindows;

    private void SwitchToNewTab() {
	parentWindow = driver.getWindowHandle();
	allwindows = driver.getWindowHandles();
	for (String childWindow : allwindows) {
	    if(!childWindow.equals(parentWindow)) {
	        driver.switchTo().window(childWindow);
	    }
	}
    }
	
    private void SwitchToParentTab() {
	driver.close();    
    	driver.switchTo().window(parentWindow);
    }
	
    @BeforeClass
    public void setupWebDriver() {
	System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
	parentWindow = driver.getWindowHandle();
	allwindows= driver.getWindowHandles();
    }
	
	
    @Test (priority=1)
    public void Scenario1() {
    	driver.get("https://www.traderev.com/en-ca/");
    	driver.findElement(By.xpath("//a[text()=' Careers ']")).click();
    	SwitchToNewTab();
    	driver.findElement(By.xpath("//a[@title='Canadian Jobs']")).click();
    	SwitchToParentTab();
    	SwitchToNewTab();
    	Assert.assertEquals(driver.getCurrentUrl(),"https://jobs.lever.co/traderev");
    	Assert.assertEquals(driver.getTitle(),"TradeRev");
    	Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Engineering']")).isDisplayed());
    	Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Support']")).isDisplayed());
    	SwitchToParentTab();
    }
    
    @Test (priority=2)
    public void Scenario2() {
    	driver.get("https://jobs.lever.co/traderev");
    	driver.findElement(By.xpath("//div[text()='Location']")).click();
    	driver.findElement(By.xpath("//a[text()='Toronto, Ontario, Canada']")).click();
    	List<WebElement> locationlabels  = driver.findElements(By.className("sort-by-location"));
    	for (WebElement label: locationlabels) {
            Assert.assertEquals(label.getText(),"TORONTO, ONTARIO, CANADA");
    	}
    }

    @Test (priority=3)
    public void Scenario3() {
    	driver.get("https://jobs.lever.co/traderev");
    	driver.findElement(By.xpath("//div[text()='Location']")).click();
    	driver.findElement(By.xpath("//a[text()='Toronto, Ontario, Canada']")).click();
    	driver.findElement(By.xpath("//div[text()='Team']")).click();
    	driver.findElement(By.xpath("//a[text()='Engineering']")).click();
    	List<WebElement> locationlabels  = driver.findElements(By.className("sort-by-location"));
    	List<WebElement> teamlabels  = driver.findElements(By.className("sort-by-team"));
    	for (WebElement label: locationlabels) {
            Assert.assertEquals(label.getText(),"TORONTO, ONTARIO, CANADA");
    	}
    	for (WebElement label: teamlabels) {
            Assert.assertEquals(label.getText().substring(0, 8),"ENGINEER");
    	}
    	
    	//log the total available positions listed
    	String log = "Total Available jobs for scenario 3 is: ";
    	log = log + locationlabels.size();
    	Reporter.log(log);
    }
    
    @AfterClass
    public void cleanup() {
        driver.close();
        driver.quit();
    } 
}
