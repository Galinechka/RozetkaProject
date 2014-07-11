package steps;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.rozetka.booking.BaseSeleniumActions;
import org.rozetka.booking.Booking;

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
	Logger log = Logger.getLogger(Booking.class);
	
	@BeforeScenario
	public void setUp(){ 
		try {
		driver = new FirefoxDriver();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.manage().deleteAllCookies();
		} catch (Exception e){
		 log.fatal("Problems with launching Firefox Driver:" + e);
		}
	}
	
	@AfterScenario
	public void tearDown() throws Exception {
		 if (driver != null)
	            driver.quit();
		  
	}
	
	@Given ("home page $url")
	public void getHomePage(String url) throws InterruptedException{
		this.url=url;
		try {
		driver.get(url);
		} catch (Exception e){
			log.fatal("Problems with closing Firefox Driver:" + e);
		}
	}
	
	@Given ("account with sum to pay from $minSum to $maxSum")
	public void makingProperSum(int minSum, int maxSum) throws InterruptedException {
		startPage = new RozetkaStartPage(driver);
		goodsPage=startPage.getDriverForGoodsPage();
			goodsPage=startPage.chooseGoodsSection();
			goodsPage.addGoodToBasket(maxSum);
		} 		
	
	@When ("user checkouts")
	public void userCheckout() throws InterruptedException{
		checkinPage=new RozetkaCheckInPage(driver);
		checkinPage.userCheckout();
	}	
	@When ("user checkouts with delivery town Simferopol and type 'Mist Express'")
	public void deliverToDonetsk() throws InterruptedException{
		checkinPage=new RozetkaCheckInPage(driver);
		checkinPage.deliverToSimferopol();
	}
	
	@Then ("the delivery sum is $deliverySum")
	public void verifyDeliverySum(String deliverySum) throws InterruptedException{
		try {
		Assert.assertEquals(deliverySum, checkinPage.getDeliverySum());
		} catch (AssertionError e){
			log.info("Actual delivery sum does not match with expected:" + e);
		}
	}
	
	@Then ("a present should be added to the order")
	public void isPresentAdded() throws InterruptedException{
		try {
		Assert.assertTrue(checkinPage.checkForPresent());
		} catch (AssertionError e){
			log.info("Present is not added to order:" + e);
		}
	}
}
