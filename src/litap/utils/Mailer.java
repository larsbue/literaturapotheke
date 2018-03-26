package litap.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import litap.model.Complain;

public class Mailer {

	public static boolean sendmail(Complain complain) {
	      // Recipient's email ID needs to be mentioned.
	      String to = "admin@litap.local";

	      // Sender's email ID needs to be mentioned
	      String from = "no-replay@litap.local";

	      // Assuming you are sending email from localhost
	      String host = "localhost";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);

	      try {
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject("New Complain");

	         // Now set the actual message
	         message.setText("Complain: " + complain.getComplain());

	         // Send message
	         Transport.send(message);
	         return true;
	      } catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	      return false;
	}
}
