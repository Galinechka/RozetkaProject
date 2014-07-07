package steps;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;

import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rozetka.booking.BaseSeleniumActions;

import pages.RozetkaCheckInPage;
import pages.RozetkaGoodsPage;
import pages.RozetkaStartPage;

public class RozetkaBookingSteps extends Steps {

	public RozetkaBookingSteps() throws Exception {
		
		super();
		driver = BaseSeleniumActions.getWebDriver();
	}
	
	private WebDriver driver;
	private String url;
	
	public RozetkaStartPage startPage;
	public RozetkaCheckInPage checkinPage;
	public RozetkaGoodsPage goodsPage;
	
	private StringBuffer verificationErrors = new StringBuffer();
	
	
	@BeforeScenario
	public void setUp(){
		 driver = new FirefoxDriver();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.manage().deleteAllCookies();
	}
	
	@AfterScenario
	public void tearDown() throws Exception {
		 if (driver != null)
	            driver.quit();
		  
	}
	
	@Given ("home page $url")
	public void getHomePage(String url) throws InterruptedException{
		this.url=url;
		driver.get(url);
		//checkinPage=new RozetkaCheckInPage(driver);
	}
	
	@Given ("account with sum to pay from $minSum to $maxSum")
	public void makingProperSum(int minSum, int maxSum) throws InterruptedException {
		startPage = new RozetkaStartPage(driver);
		goodsPage=startPage.getDriverForGoodsPage();
		//while (goodsPage.getCurrentBalanceInBasket()<minSum){
			goodsPage=startPage.chooseGoodsSection();
			goodsPage.addGoodToBasket(maxSum);
		//} 
		} 		
	
	@When ("user checkouts")
	public void userCheckout() throws InterruptedException{
		checkinPage=new RozetkaCheckInPage(driver);
		checkinPage.userCheckout();
	}	
	@When ("user checkouts with delivery town Donetsk and type 'Mist Express'")
	public void deliverToDonetsk() throws InterruptedException{
		checkinPage=new RozetkaCheckInPage(driver);
		checkinPage.deliverToDonetsk();
	}
	
	@Then ("the delivery sum is $deliverySum")
	public void verifyDeliverySum(String deliverySum) throws InterruptedException{
		Assert.assertEquals(deliverySum, checkinPage.getDeliverySum());
	}
	
	@Then ("a present should be added to the order")
	public void isPresentAdded() throws InterruptedException{
		Assert.assertTrue(checkinPage.checkForPresent());
	}
}
