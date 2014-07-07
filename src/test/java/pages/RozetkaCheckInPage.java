package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class RozetkaCheckInPage extends Page {

	
		public RozetkaCheckInPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
		String login="galinechka@gmail.com";
		String pass="ufkbyf1";
	
	 
	  public void openEditBasketPage() throws InterruptedException{
			 driver.get("http://my.rozetka.com.ua/checkout/");
			 //кнопка Редактировать заказ
				driver.findElement(By.xpath("//a[@class='xhr no-visited check-edit-order-link']")).click();
	  }
	 
		 public Boolean isElementPresent(String xpathOfElement){
			 try {
				return driver.findElement(By.xpath(xpathOfElement)).isDisplayed();
				  } catch (NoSuchElementException e) {
					  return false;
				  }
		 }
		 
		 public void userCheckout() throws InterruptedException{
			 driver.get("http://my.rozetka.com.ua/checkout/");
			 //поле Имя и фамилия:
			 driver.findElement(By.xpath("//div[@class='check-step-part']/div/div/div[2]/input")).sendKeys("Имя фамилия");
			 //поле Город:
			 driver.findElement(By.xpath("//span[@class='xhr']")).click();
			 driver.findElement(By.xpath("//div[@class='header-city-box clearfix']/div[2]/div/a")).click();
			 //поле Мобильный телефон:
			 driver.findElement(By.id("reciever_phone")).sendKeys("0673104445");
			//кнопка Далее
			 driver.findElement(By.id("reciever_phone")).sendKeys(Keys.ENTER);
			//выбор способа доставки Курер
			Thread.sleep(3000);
			driver.findElement(By.xpath("//li[@name='couriers']/a")).click();
			
		 }
		 public void deliverToDonetsk() throws InterruptedException{
			 driver.get("http://my.rozetka.com.ua/checkout/");
			 //поле Имя и фамилия:
			 driver.findElement(By.xpath("//div[@class='check-step-part']/div/div/div[2]/input")).sendKeys("Имя фамилия");
			 //поле Город:
			 driver.findElement(By.xpath("//span[@class='xhr']")).click();
			 driver.findElement(By.linkText("Донецк")).click();
			 //driver.findElement(By.xpath("//div[@class='header-city-box clearfix']/div[2]/div/a")).click();
			 //поле Мобильный телефон:
			 driver.findElement(By.id("reciever_phone")).sendKeys("0673104445");
			//кнопка Далее
			 driver.findElement(By.id("reciever_phone")).sendKeys(Keys.ENTER);
			//выбор способа доставки Курер
			Thread.sleep(3000);
			driver.findElement(By.xpath("//li[@name='couriers']/a")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[@class='check-f-i-field']/div[2]/ul/li[2]/div/label")).click();
			Thread.sleep(3000);
		 }
		 public String getDeliverySum() throws InterruptedException{
			 Thread.sleep(5000);
			 String deliverySum=driver.findElement(By.xpath("//div[@name='total']/div[2]/div[2]")).getText();
			 deliverySum=deliverySum.replaceAll("[^0-9]", "");
			 if (deliverySum.isEmpty()) deliverySum="0";
			 return deliverySum;
		 }
		 public Boolean checkForPresent() throws InterruptedException{
			 openEditBasketPage();
			 Boolean presentIsPresent=false;
				int counter=1;
				String currentPrice;
				String xpathForPrice="//div[@class='check-order']/div["+counter+"]/div[3]/div";
				while(isElementPresent(xpathForPrice) & presentIsPresent==true){
					currentPrice=driver.findElement(By.xpath(xpathForPrice)).getText();
					currentPrice=currentPrice.replaceAll("[^0-9]", "");
					presentIsPresent=(currentPrice=="" || currentPrice=="0");
				}
				return presentIsPresent;
		 }
}
