package week4.day1;

import java.util.ArrayList;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Chittorgarh {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.get("https://www.chittorgarh.com/");
		WebElement stockmarket = driver.findElement(By.id("navbtn_stockmarket"));
		stockmarket.click();

		WebElement nsebulk = driver.findElement(By.linkText("NSE Bulk Deals"));
		nsebulk.click();
		Thread.sleep(2000);

		WebElement table = driver.findElement(By.xpath("//table[@class='table table-bordered table-condensed table-striped']"));
		List<WebElement> rowsList = table.findElements(By.tagName("tr"));
		System.out.println("no of rows "+ rowsList.size());
		List<WebElement> columns = table.findElements(By.tagName("th"));
		System.out.println("no of columns "+ columns.size());
		List<String> list = new ArrayList<>();
		for (int i=1;i<(rowsList.size()-1);i++) {

			String header= driver.findElement(By.xpath("//div/table/thead/tr/th/a[text()='Security Name']")).getText();
			if(header.contains("Security Name")) {
				String allvalues= driver.findElement(By.xpath("//div/table/tbody/tr[" + (i + 1) + "]/td[3]")).getText();
				System.out.println(" Security values- "+ allvalues);
				list.add(allvalues);

			}
		}
		boolean flag;
		for (int i=0;i<list.size();i++) {
			for(int j=i+1;j<list.size();j++) {
				String list1 = list.get(i);
				String list2 = list.get(j);
				if(list1.contains(list2)) {
					flag= true;}
					else {
						flag= false;	}
				}
			}
		if (flag=true)
		{System.out.println("Duplicates found");}
		else
		{
			System.out.println("Duplicates not found");}
}
}


