package week4.day1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TableTestLeaf {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/table.html");
		//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(2000);
		WebElement table = driver.findElement(By.xpath("//table[@cellspacing='0']"));
		List<WebElement> row = table.findElements(By.tagName("tr"));		
		List<WebElement> column = table.findElements(By.tagName("th"));

		// no of rows and columns
		System.out.println("count of rows= "+ row.size());
		System.out.println("count of columns= "+ column.size());

		//Get the progress value of 'Learn to interact with Elements'
		int mini=0; int count=0;
		for(int i=1;i<row.size();i++) {

			for (int j=1;j<column.size();j++) {

				String rownames = driver.findElement(By.xpath("//table//tr[" + (i + 1) + "]//td[" + j + "]")).getText();
				

				if(rownames.contains("Learn to interact with Elements")) {
					String columnpercent= driver.findElement(By.xpath("//table//tr[" + (i+1) + "]//td[2]")).getText();
					System.out.println(rownames +" "+ columnpercent);
				}
			}

			String columnpercent1 = driver.findElement(By.xpath("//table//tr[" + (i + 1) + "]//td[2]")).getText();
			String columnpercent2 = columnpercent1.replaceAll("\\D", "");
			int percent = Integer.parseInt(columnpercent2);
			if (percent < count) {
				mini=percent;
				count=mini;

			}
			else {
				count=percent;
			}
		}

		//	Check the vital task for the least completed progress.
		for (int i = 1; i < row.size(); i++) {
			for (int j = 1; j <= column.size(); j++) {
				WebElement colname = driver.findElement(By.xpath("//table//tr[" + (i + 1) + "]//td[" + j + "]"));
				String colname3 = colname.getText();
		//		System.out.println("****"+colname3+"*****");
				if (colname3.contains(Integer.toString(mini)))
				{
					driver.findElement(By.xpath("//table//tr[" + (i + 1) + "]//td[3]//input")).click();
					break;
				}
			}


		}
	}
}


