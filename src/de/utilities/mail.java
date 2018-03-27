package de.utilities;

import java.util.Properties;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * Gr��tenteils von https://www.mkyong.com/java/javamail-api-sending-email-via-gmail-smtp-example/
 * �bernommen.
 * Methoden und �bergabeparameter leicht ver�ndert.
 * 
 * Diese Klasse versendet eine Mail an die E-mail Adresse: sportweb233@gmail.com
 * @author Benjamin Gajewski
 *
 */
public class Mail {
	
	/**
	 * Zugangsdaten
	 */
	private static final String USERNAME = "sportweb233@gmail.com";
	private static final String PASSWORD = "Sportweb123";
	/**
 	* Diese Methode versendete eine Mail. Als erstes wird eine Verbindung mit den Server hergestellt und dann wird
 	* die Mail angeschickt.
 	* @param recipient Empf�nger
 	* @param subject Betreff
 	* @param text Beschreibung
 	*/
	public static void SendMailTLS(String recipient, String subject, String text) {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(USERNAME));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
			message.setSubject(subject);
			message.setText(text);
			Transport.send(message);
			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	
}
