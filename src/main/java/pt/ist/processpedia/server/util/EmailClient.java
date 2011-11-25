package pt.ist.processpedia.server.util;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailClient {

  public final static String smtpServer = "stmp.server.com";
  public final static String securePort = "465";
  public final static String email = "email";
  public final static String username = "username";
  public final static String password = "password";
  public final static String activationUrl = "url";

  public static void sendActivationEmail(String name, String email, String activationKey) {

		Properties props = new Properties();
		props.put("mail.smtp.host", smtpServer);
		props.put("mail.smtp.socketFactory.port", securePort);
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");


    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username,password);
				}
			});
    try {
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(email));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

      message.setSubject("Activate your Processpedia account");

      message.setText("Dear "+name+",\n\nPlease activate your account using the following URL:\n"+activationUrl+":"+activationKey);

      Transport.send(message);

    } catch (AddressException e) {
      e.printStackTrace();
    } catch (MessagingException e) {
      e.printStackTrace();
    }
    
  }

}
