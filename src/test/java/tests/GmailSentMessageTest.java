package tests;

import org.testng.annotations.Test;

import datadriven.jsonDataReader;
import pages.GmailSendEmailTLS;
import pages.GmailSentMessage;
import pages.GoogleHomePage;
import pages.SignInEmail;
import pages.WelcomeSetPassword;

public class GmailSentMessageTest extends BaseTest  {
	jsonDataReader jsonReader = new jsonDataReader();
	GoogleHomePage googleTest;
	SignInEmail emailGmail;
	WelcomeSetPassword passwordGmail;
	GmailSendEmailTLS sentMessageAuto;
	GmailSentMessage sentMessage;
	
	@Test(priority=1, groups="ValidateSentMail")
	public void GmailVerifySentMessage() throws Exception{
		jsonReader.jsonReaderGoogleData();
		googleTest = new GoogleHomePage(driver);
		googleTest.searchForData(jsonReader.googleSearchBox);
		
		emailGmail =  new SignInEmail(driver);
		emailGmail.setEmailOrPhone(jsonReader.gmailUsername);
		
		passwordGmail =  new WelcomeSetPassword(driver);
		passwordGmail.setPassword(jsonReader.gmailPassword);
		
		sentMessageAuto = new GmailSendEmailTLS();
		//sentMessageAuto.sendMail(jsonReader.gmailUsername, jsonReader.gmailPassword, jsonReader.gmailFrom, jsonReader.gmailTo, jsonReader.gmailSubject, jsonReader.gmailText, jsonReader.gmailFile, jsonReader.gmailFileName);
		
		sentMessage = new GmailSentMessage(driver);
		sentMessage.VerifySentMessage(
				jsonReader.gmailUsername, 
				jsonReader.gmailPassword, 
				jsonReader.gmailFrom, 
				jsonReader.gmailTo, 
				jsonReader.gmailSubject, 
				jsonReader.gmailText, 
				jsonReader.gmailFile, 
				jsonReader.gmailFileName, 
				jsonReader.gmailInbox
		);
	}
}