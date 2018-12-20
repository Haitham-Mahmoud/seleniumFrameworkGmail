package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SignInEmail extends BasePage{
	//*********PageFactory - WebElements*********
	@FindBy(id="identifierId") public WebElement EmailPhoneGmail;
	@FindBy(xpath="//div[contains(text(),'Email or phone')]") public WebElement EmailPhoneAssert;
	@FindBy(id="identifierNext") public WebElement EmailPhoneNextButton;

	//*********Constructor*********
	public SignInEmail(WebDriver driver){
		super(driver);
	}
	public void setEmailOrPhone(String emailPhone){
		WebDriverWait wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Email or phone')]")));
		Assert.assertEquals(EmailPhoneAssert.getText(), "Email or phone");
		sendKeysPrintText(EmailPhoneGmail, emailPhone);
		clickButton(EmailPhoneNextButton);
	}
}
