package hariKrishna.DataDriven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SqlData extends Utility {

	@Test(dataProvider = "sql")
	public void dataTest(String user, String pass) {
		driver.findElement(By.id("username")).sendKeys(user);
		driver.findElement(By.id("password")).sendKeys(pass);
		driver.findElement(By.id("Login")).click();
		Assert.assertEquals(
				"Please check your username and password. If you still can't log in, contact your Salesforce administrator.",
				driver.findElement(By.id("error")).getText());
	}

	@DataProvider(name = "sql")
	public Object[][] getSql() throws SQLException {
		Object[][] data = getSqlData("root", "Krish@59", "fail", "select * from loginfail where id = '1'");
		Object[][] data1 = getSqlData("root", "Krish@59", "fail", "select * from loginfail where id = '2'");
		Object[][] data2 = getSqlData("root", "Krish@59", "fail", "select * from loginfail where id = '3'");
		Object[][] data3 = getSqlData("root", "Krish@59", "fail", "select * from loginfail where id = '4'");
		return new Object[][] { { data[0][0], data[0][1] }, { data1[0][0], data1[0][1] }, { data2[0][0], data2[0][1] },
				{ data3[0][0], data3[0][1] } };

	}

	public Object[][] getSqlData(String user, String pass, String dataBase, String query) throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mySql://localhost:3306/" + dataBase, user, pass);
		Statement sat = con.createStatement();
		ResultSet res = sat.executeQuery(query);
		Object[][] data = new Object[1][2];
		while (res.next()) {
			data[0][0] = res.getObject("username");
			data[0][1] = res.getObject("password");
		}
		return data;
	}
}
