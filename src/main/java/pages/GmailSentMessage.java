package pages;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailSentMessage  extends BasePage{
	//*********PageFactory - WebElements*********
	@FindBy(className="z0") public WebElement ComposeButton;
	@FindBy(name="to") public WebElement ToEmail;
	@FindBy(name="subjectbox") public WebElement SubjectEmail;
	@FindBy(className="editable") public WebElement BodyEmail;
	@FindBy(className="a1") public WebElement AttachEmail;
	@FindBy(xpath="//*[contains(@class, 'aoO')]") public WebElement SendButton;
	@FindBy(xpath="//span[@class='bog']") public List<WebElement> Inbox;
	@FindBy(xpath="//div[@class='aQH']/span/a/span") public WebElement DownloadArea;
	//*********Constructor*********
	public GmailSentMessage(WebDriver driver){
		super(driver);
	}
	public void VerifySentMessage(
			String emailUsername, 
			String emailPassword, 
			String mailFrom, 
			String mailTo, 
			String mailSubject,
			String mailText,
			String file,
			String fileName,
			String InboxMail) throws AWTException, HeadlessException, UnsupportedFlavorException, IOException, InterruptedException{

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("z0")));
		clickButton(ComposeButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("to")));
		sendKeysPrintText(ToEmail, mailTo);
		sendKeysPrintText(SubjectEmail, mailSubject);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("editable")));
		sendKeysPrintText(BodyEmail, mailText);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("a1")));
		clickButton(AttachEmail);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(file), null);
		System.out.println(((String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor)));
		Robot rb1 = new Robot();
		rb1.delay(1000);
		rb1.keyPress(KeyEvent.VK_CONTROL);
		rb1.keyPress(KeyEvent.VK_V);
		rb1.keyRelease(KeyEvent.VK_V);
		rb1.keyRelease(KeyEvent.VK_CONTROL);
		rb1.keyPress(KeyEvent.VK_ENTER);
		rb1.keyRelease(KeyEvent.VK_ENTER);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dO")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'aoO')]")));
		clickButton(SendButton);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		for (int i=0; i<Inbox.size();i++) {
			if(Inbox.get(i).getText().contains(mailSubject)) {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='bog']")));
				Inbox.get(i).click();
				System.out.println("Message opened successfully");
			}
		}
		if(DownloadArea.getAttribute("innerHTML").contains(fileName)) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@aria-label,'Download attachment "+fileName+"')]")));
			driver.findElement(By.xpath("//*[contains(@aria-label,'Download attachment "+fileName+"')]")).click();
			Thread.sleep(10000);
			//Xpath clarification below
			//*[contains(@aria-label,'Download attachment selenium-tutorial.jpg')]
		}
	}

}
