package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class WelcomeSetPassword  extends BasePage{
	//*********PageFactory - WebElements*********
	@FindBy(name="password") public WebElement PasswordGmail;
	@FindBy(xpath="//div[contains(text(),'Enter your password')]") public WebElement PasswordAssert;
	@FindBy(id="passwordNext") public WebElement PasswordNextButton;

	//*********Constructor*********
	public WelcomeSetPassword(WebDriver driver){
		super(driver);
	}
	public void setPassword(String passWord){
		WebDriverWait wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Enter your password')]")));
		Assert.assertEquals(PasswordAssert.getText(), "Enter your password");
		sendKeysPrintText(PasswordGmail, passWord);
		clickButton(PasswordNextButton);
	}
}