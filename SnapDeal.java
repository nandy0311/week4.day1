package week4.day1;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); This is not working
		Thread.sleep(2000);
		WebElement MensFash = driver.findElement(By.xpath("(//span[@class='catText'])[1]"));
		MensFash.click();
		WebElement sportsshoe = driver.findElement(By.xpath("(//span[text()='Sports Shoes'])[1]"));
		sportsshoe.click();
		//show count is not working
		/*	WebElement sportsshoecount = driver.findElement(By.xpath("//span[@class='category-name category-count']"));
		String shoecount= sportsshoecount.getText();
		System.out.println("Total sportsshoecount is" + shoecount);	 */
		Thread.sleep(8000);

		//	training shoe- another xpath-(//div[@class='child-cat-name '])[2]
		WebElement trainingshoes = driver.findElement(By.xpath("(//a[@class='child-cat-node dp-widget-link hashAdded']/div[@class='child-cat-name '])[2]"));
		trainingshoes.click();

		Thread.sleep(2000);
		WebElement trainingshoesort = driver.findElement(By.xpath("//div[@class='sort-drop clearfix']"));
		trainingshoesort.click();
		WebElement trainingshoesortlh = driver.findElement(By.xpath("(//ul[@class='sort-value'])/li[@data-sorttype='plth']"));
		trainingshoesortlh.click();
		Thread.sleep(9000); 


		List<WebElement> price = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		for (WebElement webElement : price) {
			String priceamount=webElement.getText();
			System.out.println(priceamount);			
		}
		int price1amt=0,price2amt=0;
		for (int i=0;i<price.size();i++) {
			for (int j=i+1; j<price.size();j++) {
				String price1 = price.get(i).getText();
				price1 = price1.replaceAll("[^0-9]", "");
				price1amt = Integer.parseInt(price1); //string to integer
				String price2 = price.get(j).getText();
				price2 = price2.replaceAll("[^0-9]", "");
				price2amt = Integer.parseInt(price2);}}
		if (price1amt < price2amt) {
			System.out.println(" correctly sorted from low to high");
		}else {
			System.out.println("INcorrectly sorted from low to high");
		}
		//alternative to continue code for practise- proceeding with next step


		driver.get("https://www.snapdeal.com/products/mens-training-shoes?sort=plth#bcrumbLabelId:255");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//Find element by link text and store in variable "Element"        		
		WebElement bluecheckbox = driver.findElement(By.xpath("//input[@value='Blue']/following-sibling::label[1]"));
		//"//input[@value='Blue']/following-sibling::label[1]"
		////label[@for='Color_s-Blue']
		//This will scroll the page till the element is found		
		js.executeScript("arguments[0].scrollIntoView();", bluecheckbox);
		if (bluecheckbox.isEnabled()) {
			System.out.println("blue checkbox is enabled");
			// bluecheckbox not selected during run ****
			bluecheckbox.click();
		}

		//	WebElement bluefilterresult = driver.findElement(By.xpath("//img[@class='product-image wooble']"));
		//	bluefilterresult.click();


		driver.get("https://www.snapdeal.com/products/mens-training-shoes?sort=plth&q=Color_s%3ABlue%7C#bcrumbLabelId:255");
		driver.manage().window().maximize();
		//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); This is not working
		Thread.sleep(8000);
		//https://www.snapdeal.com/products/mens-training-shoes?sort=plth&q=Color_s%3ABlue%7C#bcrumbLabelId:255

		String window1 = driver.getWindowHandle();
		System.out.println("Parent Window " + window1);


		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//img[@class='product-image wooble']")));
		action.build().perform();
		// click QuickView button
		driver.findElement(By.xpath("//div[@class='clearfix row-disc']/div")).click();
		// Print the cost and the discount percentage
		String window2 = driver.getWindowHandle();
		System.out.println("Window2 " + window2);
		String shoeprice = driver.findElement(By.className("payBlkBig")).getText();
		System.out.println("Product price is: " + shoeprice);
		String shoediscount = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
		System.out.println("Product discount is: " + shoediscount);


		File screenshot = driver.getScreenshotAs(OutputType.FILE);
		File destifolder = new File("C:\\Users\\user\\eclipse-workspace\\MavenProject\\src\\main\\java\\screenshot\\shoe.png");
		FileUtils.copyFile(screenshot, destifolder);
		// Close the current window
		driver.close();
	} }