package week4.day1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TagsTable {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://html.com/tags/table/");
		WebElement table = driver.findElement(By.xpath("//div[@class='render']/table"));
		List<WebElement> row = table.findElements(By.tagName("tr"));
	//	WebElement firstrow= driver.findElement(By.xpath("//div[@class='render']/table/thead/tr"));
	//	List<WebElement> column = firstrow.findElements(By.tagName("th"));
		List<WebElement> column1 = table.findElements(By.tagName("th"));
		System.out.println(row.size());
//		System.out.println(column.size());
		System.out.println(column1.size());
		for (WebElement webElement : row) {
			String rownames=webElement.getText();
		System.out.println(rownames);	
		}

	}

}
