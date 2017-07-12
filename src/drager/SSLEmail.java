package drager;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class SSLEmail {

	/**
	 * Outgoing Mail (SMTP) Server requires TLS or SSL: smtp.gmail.com (use
	 * authentication) Use Authentication: Yes Port for SSL: 465
	 */
	public static void sendCode(String toEmail, int code) {
		// System.out.println("email");
		String fromEmail = "***********@mail.sustc.edu.cn"; // requires valid email id
		String password = "*************"; // correct password for email id
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.exmail.qq.com"); // SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); // SSL Port
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL
																						// Factory
																						// Class
		props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); // SMTP Port
		// System.out.println("email2");

		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		// System.out.println("email3");
		Session session = Session.getDefaultInstance(props, auth);
		EmailUtil.sendEmail(session, toEmail, "Java自助学习系统验证", "同学你好，你的验证码是：" + code);
		// System.out.println("email4");

	}

}