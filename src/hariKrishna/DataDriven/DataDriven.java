package hariKrishna.DataDriven;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDriven extends Utility {

	@Test(dataProvider = "object", enabled = false)
	public void dataTest(String user, String pass) {
		driver.findElement(By.id("username")).sendKeys(user);
		driver.findElement(By.id("password")).sendKeys(pass);
		driver.findElement(By.id("Login")).click();
		Assert.assertEquals(
				"Please check your username and password. If you still can't log in, contact your Salesforce administrator.",
				driver.findElement(By.id("error")).getText());
	}

	@Test(dataProvider = "hash")
	public void datahashTest(String user, String pass) {
		driver.findElement(By.id("username")).sendKeys(user);
		driver.findElement(By.id("password")).sendKeys(pass);
		driver.findElement(By.id("Login")).click();
		Assert.assertEquals(
				"Please check your username and password. If you still can't log in, contact your Salesforce administrator.",
				driver.findElement(By.id("error")).getText());
	}

	@DataProvider(name = "object")
	public Object[][] adata() {
		Object[][] data = new Object[2][2];
		data[0][0] = "hk@email.com";
		data[0][1] = "21968432132";
		data[1][0] = "vfhk@email.com";
		data[1][1] = "fwy47968432132";
		return data;
	}

	@DataProvider(name = "hash")
	public Object[][] hashdata() {
		HashMap<String, String> data = new HashMap<>();
		data.put("email", "hk@email.com");
		data.put("pass", "21968432132");
		HashMap<String, String> data1 = new HashMap<>();
		data1.put("email", "51hk@email.com");
		data1.put("pass", "2346468432132");
		return new Object[][] { { data.get("email"), data.get("pass") }, { data1.get("email"), data1.get("pass") } };
	}

}
