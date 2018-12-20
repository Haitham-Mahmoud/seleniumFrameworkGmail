package pages;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	public WebDriver driver;
	//public WebElement element;

	//Create Constructor
	public BasePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Click action
	public static void clickButton(WebElement button) {
		button.click();
	} 
	//Click action
	public static void clickButtonPrintAction(WebElement button, String text) {
		button.click();
		System.out.println("You clicked :" + text);
	}

	//Write text
	public static void sendKeysPrintText(WebElement textbox, String text) {
		textbox.clear();
		textbox.sendKeys(text);
		System.out.println("You entered the value :" + text);
	}

	//Use Keyboard
	public static void sendKeyBorad(WebElement element, Keys Key) {
		element.sendKeys(Key);
	}

	public static void selectDiv(WebElement element, String text) {
		List<WebElement> optionsUtilization= element.findElements(By.tagName("div"));
		for (WebElement option1 : optionsUtilization) 
		{
			if(text.equals(option1.getText())){
				option1.click();
				System.out.println("You selected the value :" + text);
			}
		}
	}

}
