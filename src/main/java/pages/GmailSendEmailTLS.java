package pages;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

public class GmailSendEmailTLS {

	public void sendMail(String emailUsername, String emailPassword, String mailFrom, String mailTo, String mailSubject,
			String mailText,String file,String fileName) throws Exception {

		Properties config = createConfiguration();

		// Creates a mail session. We need to supply username and
		// password for Gmail authentication.
		Session session = Session.getInstance(config, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailUsername,emailPassword);
			}
		});

		// Creates email message
		Message message = new MimeMessage(session);
		message.setSentDate(new Date());
		message.setFrom(new InternetAddress(mailFrom));
		message.setRecipient(Message.RecipientType.TO,
				new InternetAddress(mailTo));
		message.setSubject(mailSubject);
		message.setText(mailText);
		MimeBodyPart messageBodyPart = new MimeBodyPart();

        Multipart multipart = new MimeMultipart();

        messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(file);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);

        System.out.println("========== Sending .... ==========");

        // Send a message
     	Transport.send(message, emailUsername, emailPassword);

        System.out.println("========== Done ==========");
	}

	private Properties createConfiguration() {
		return new Properties() {{
			put("mail.smtp.auth", "true");
			put("mail.smtp.host", "smtp.gmail.com");
			put("mail.smtp.port", "587");
			put("mail.smtp.starttls.enable", "true");
			put("mail.smtp.ssl.trust", "smtp.gmail.com");
		}};
	}

}
