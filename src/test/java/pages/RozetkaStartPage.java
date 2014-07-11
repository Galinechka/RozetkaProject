package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.rozetka.booking.Booking;

public class RozetkaStartPage extends Page{

	Logger log = Logger.getLogger(Booking.class);
	
	 public  RozetkaStartPage (WebDriver driver) {
	        super(driver);
	    }

	 public void openHomePage(String url) {
			driver.get(url);
		}
	 
	 public RozetkaGoodsPage chooseGoodsSection(){
		//открывает раздел Детский мир
			driver.get("http://rozetka.com.ua");
			try {
			driver.get(driver.findElement(By.xpath("//td[@id='tv-photo-video']/div/a")).getAttribute("href"));
			//выбирает раздел спальники
			driver.findElement(By.xpath("//ul[@class='m-cat-subl']/li[5]/a")).click();
			} catch (Exception e){
				log.error("Problems with opening proper section"+e);
			}
			return new RozetkaGoodsPage(driver);
	 }
	 public RozetkaGoodsPage getDriverForGoodsPage(){
		 return new RozetkaGoodsPage(driver);
	 }
		
}
