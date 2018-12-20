package datadriven;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import utilities.Constant;

public class jsonDataReader {

	public String googleSearchBox, 
	gmailUsername, 
	gmailPassword, 
	gmailFrom, 
	gmailTo, 
	gmailSubject, 
	gmailText, 
	gmailFile, 
	gmailFileName,
	gmailInbox;
	public void jsonReaderGoogleData() throws FileNotFoundException, IOException, ParseException {
		File srcFile = new File(Constant.FileTestData);
		JSONParser parser = new JSONParser();
		JSONArray jArray = (JSONArray)parser.parse(new FileReader(srcFile));
		for (Object jsonObj : jArray) {
			JSONObject gData =  (JSONObject) jsonObj;
			googleSearchBox = (String) gData.get("searchkey");
			gmailUsername = (String) gData.get("username");
			gmailPassword = (String) gData.get("password");
			gmailFrom = (String) gData.get("mailFrom");
			gmailTo = (String) gData.get("mailTo");
			gmailSubject = (String) gData.get("mailSubject");
			gmailText = (String) gData.get("mailText");
			gmailFile = (String) gData.get("file");
			gmailFileName = (String) gData.get("fileName");
			gmailInbox = (String) gData.get("inbox");
			 
		}
	}
}
