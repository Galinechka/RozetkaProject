package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.rozetka.booking.Booking;

public class RozetkaCheckInPage extends Page {

	Logger log = Logger.getLogger(Booking.class);
	
		public RozetkaCheckInPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
		String login="galinechka@gmail.com";
		String pass="ufkbyf1";
	
	 
	  public void openEditBasketPage() throws InterruptedException{
			 driver.get("http://my.rozetka.com.ua/checkout/");
			 //кнопка Редактировать заказ
			 try {
				driver.findElement(By.xpath("//a[@class='xhr no-visited check-edit-order-link']")).click();
			 } catch (Exception e){
				 log.error("Could not find button:" + e);
			 }
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
			 try {
			 driver.findElement(By.xpath("//div[@class='check-step-part']/div/div/div[2]/input")).sendKeys("Имя фамилия");
			 } catch (Exception e){
				 log.error("Could not find input:" + e);
			 }
			 //поле Город:
			 try {
			 driver.findElement(By.xpath("//span[@class='xhr']")).click();
			 } catch (Exception e){
				 log.error("Could not find field" + e);
			 }
			 try {
			 driver.findElement(By.xpath("//div[@class='header-city-box clearfix']/div[2]/div/a")).click();
			 } catch (Exception e){
				 log.error("Could not find button" + e);
			 }
			 //поле Мобильный телефон:
			 try{
			 driver.findElement(By.id("reciever_phone")).sendKeys("0673104445");
			 driver.findElement(By.id("reciever_phone")).sendKeys(Keys.ENTER);
			 } catch (Exception e){
				 log.error("Could not find field" + e);
			 }
			//выбор способа доставки Курер
			Thread.sleep(3000);
			try {
			driver.findElement(By.xpath("//li[@name='couriers']/a")).click();
			 } catch (Exception e){
				 log.error("Could not find field" + e);
			 }
		 }
		 public void deliverToSimferopol() throws InterruptedException{
			 driver.get("http://my.rozetka.com.ua/checkout/");
			 //поле Имя и фамилия:
			 try {
			 driver.findElement(By.xpath("//div[@class='check-step-part']/div/div/div[2]/input")).sendKeys("Имя фамилия");
			 } catch (Exception e){
				 log.error("Could not find field" + e);
			 }
			 //поле Город:
			 try {
			 driver.findElement(By.xpath("//span[@class='xhr']")).click();
			 } catch (Exception e){
				 log.error("Could not find field" + e);
			 }
			 try{
			 driver.findElement(By.linkText("Симферополь")).click();
			 } catch (Exception e){
				 log.error("Could not find field" + e);
			 }
			 //поле Мобильный телефон:
			 try{
			 driver.findElement(By.id("reciever_phone")).sendKeys("0673104445");
			 driver.findElement(By.id("reciever_phone")).sendKeys(Keys.ENTER);
			 } catch (Exception e){
				 log.error("Could not find field" + e);
			 }
			//выбор способа доставки Курер
			Thread.sleep(3000);
			try{
			driver.findElement(By.xpath("//li[@name='couriers']/a")).click();
			} catch (Exception e){
				 log.error("Could not find field" + e);
			 }
			Thread.sleep(3000);
			try{
			driver.findElement(By.xpath("//div[@class='check-f-i-field']/div[2]/ul/li[2]/div/label")).click();
			} catch (Exception e){
			 log.error("Could not find field" + e);
			}
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
				try {
					driver.findElement(By.xpath(xpathForPrice)).isDisplayed();
				} catch (Exception e){
					 log.error("Could not find element: " + xpathForPrice + e);
				}
				while(isElementPresent(xpathForPrice) & presentIsPresent==true){
					currentPrice=driver.findElement(By.xpath(xpathForPrice)).getText();
					currentPrice=currentPrice.replaceAll("[^0-9]", "");
					presentIsPresent=(currentPrice=="" || currentPrice=="0");
				}
				return presentIsPresent;
		 }
}
