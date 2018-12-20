package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class GoogleHomePage extends BasePage{
	//*********PageFactory - WebElements*********
	@FindBy(name="q") public WebElement GoogleSearchTextbox;
	@FindBy(name="btnK") public WebElement GoogleSearchButton;
	@FindBy(xpath="//a[contains(text(),'English')]") public WebElement GoogleLangEnglish;
	@FindBy(xpath="//*[contains(text(),'Gmail - Google')]") public WebElement GoogleResultGmail;
	@FindBy(xpath="//*[contains(text(),'Sign In')]") public WebElement GmailSignIn;

	//*********Constructor*********
	public GoogleHomePage(WebDriver driver){
		super(driver);
	}


	public void searchForData(String googleSearch){
		clickButton(GoogleLangEnglish);
		sendKeysPrintText(GoogleSearchTextbox, googleSearch);
		sendKeyBorad(GoogleSearchTextbox,Keys.ENTER);
		Assert.assertEquals(GoogleResultGmail.getText(), "Gmail - Google");
		clickButton(GoogleResultGmail);
		Assert.assertEquals(GmailSignIn.getText(), "SIGN IN");
		clickButton(GmailSignIn);
	}
}
