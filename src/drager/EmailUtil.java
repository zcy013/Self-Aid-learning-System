package drager;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtil {

	/**
	 * Utility method to send simple HTML email
	 * 
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	public static void sendEmail(Session session, String toEmail, String subject, String body) {
		try {
			System.out.println("email31");
			MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			System.out.println("email32");

			msg.setFrom(new InternetAddress("wancl@mail.sustc.edu.cn"));

			msg.setReplyTo(InternetAddress.parse("wancl@mail.sustc.edu.cn", false));

			msg.setSubject(subject, "UTF-8");

			msg.setText(body, "UTF-8");

			msg.setSentDate(new Date());
			// System.out.println("email33");
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			// System.out.println("email33.5");
			Transport.send(msg);
			// System.out.println("email34");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
