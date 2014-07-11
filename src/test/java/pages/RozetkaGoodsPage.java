package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.rozetka.booking.Booking;

public class RozetkaGoodsPage extends Page{
	
	int goodsCounter=1;
	Logger log = Logger.getLogger(Booking.class);
	
	 public RozetkaGoodsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public Boolean checkIfPriceIsLessThanMaximum (int goodsCounter, int maxSum){
			if (getPriceOfCurrentGood(goodsCounter)>maxSum) {
				return false;
			} else {
				return true;
			}
	 }
	 public int findProperGood(int goodsCounter, int maxSum){
		 while (!checkIfPriceIsLessThanMaximum(goodsCounter, maxSum)){
		 goodsCounter++;
		 checkIfPriceIsLessThanMaximum(goodsCounter, maxSum);
		 }
		 return(goodsCounter);
	 }
	 
	 public int addGoodToBasket(int maxSum) throws InterruptedException{
		goodsCounter=findProperGood(goodsCounter, maxSum);
		 //нажать кнопку купить товара под номером goodsCounter
		 String currentXpath="//div[@id='block_with_goods']/div/div/div["+goodsCounter+"]/descendant::button";
		 int numberOfGoods=maxSum/(getPriceOfCurrentGood(goodsCounter));
		 try{
			driver.findElement(By.xpath(currentXpath)).sendKeys(Keys.ENTER);
		 } catch (Exception e){
			 log.error("Problems with finding button Buy good"+e);
		 }
			goodsCounter++;
			Thread.sleep(3000);
			try{
			driver.findElement(By.xpath("//div[@class='cart-info']/div[2]/div[3]/input")).clear();
			driver.findElement(By.xpath("//div[@class='cart-info']/div[2]/div[3]/input")).sendKeys(String.valueOf(numberOfGoods));
			driver.findElement(By.xpath("//div[@class='cart-info']/div[2]/div[3]/input")).sendKeys(Keys.ENTER);
			driver.findElement(By.xpath("//img[@class='popup-close-icon']")).click();
			} catch(Exception e){
				 log.error("Problems with finding field for number of goods"+e);
			}
			Thread.sleep(3000);
			return goodsCounter;
	 }
	 public int getCurrentBalanceInBasket(){
		 String basketContent=driver.findElement(By.xpath("//div[@class='wrap clearfix']/div[2]/div/div")).getText();
		 if (!(basketContent.contains("Корзина"))){
		String currentBalanceString=driver.findElement(By.xpath("//div[@class='m-cart-full']")).getText();
		 currentBalanceString=currentBalanceString.replaceAll("[^0-9]", "");
		 currentBalanceString=currentBalanceString.replaceAll("^[0-9]", "");
		 int currentBalanceInt=Integer.parseInt(currentBalanceString);
		 return currentBalanceInt;
		 } else {
			 return 0;
		 }
		 
	  }
	 public int getPriceOfCurrentGood(int goodsCounter){
		 int priceOfGood;
		//путь к цене товара под номером goodsCounter
			String currentXpath="//div[@id='block_with_goods']/div/div/div["+goodsCounter+"]/descendant::div[@class='g-price-uah']";
			try {
				driver.findElement(By.xpath(currentXpath)).isDisplayed();
			} catch (Exception e){
				log.error("Problems with finding price of good"+e);
			}
			//сохраняет цену товара под номером goodsCounter
			String priceOfGoodString=driver.findElement(By.xpath(currentXpath)).getText();
			priceOfGoodString=priceOfGoodString.replaceAll("[^0-9]", "");
			priceOfGood= Integer.parseInt(priceOfGoodString);
		 return priceOfGood;
	 }
	  
	
}
