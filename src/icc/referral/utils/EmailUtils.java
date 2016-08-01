package icc.referral.utils;

import java.text.DecimalFormat;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

import icc.referral.model.QueuedEmail;
import icc.referral.model.User;

public class EmailUtils {

	public static int EMAIL_NOT_SENT = 0;
	public static int EMAIL_SENT = 1;
	public static int EMAIL_SENT_FAILED = 2;

	public static QueuedEmail setQueuedEmail(StringBuffer message, String subject, User user) throws Exception {

		try {
			QueuedEmail queuedEmail = new QueuedEmail();
			queuedEmail.setSubject(subject);

			queuedEmail.setMessage(message.toString());
			queuedEmail.setEmailfrom("to.sujan.maharjan@gmail.com");
			queuedEmail.setCc("thesujan540@gmail.com");
			queuedEmail.setType("");

			queuedEmail.setEmailto(user.getEmail());
			queuedEmail.setStatus(EMAIL_NOT_SENT);

			System.out.println("Successfully created queued email");

			return queuedEmail;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static Double decimalFormat(Double d) throws Exception {

		DecimalFormat df = new DecimalFormat("##.##");

		return new Double(df.format(d));

	}

	public static boolean sendEmailNotification(QueuedEmail queuedEmail) throws Exception {

		try {
			SimpleEmail email = new SimpleEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(587);
			email.setFrom("thesujan540@gmail.com");
			email.setAuthenticator(new DefaultAuthenticator("thesujan540@gmail.com", "sujanfaith"));
			email.setTLS(true);

			email.addTo(queuedEmail.getEmailto());

			email.setSubject(queuedEmail.getSubject());

			email.setMsg(queuedEmail.getMessage());

			email.send();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public static void main(String[] args) {
		try {
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}