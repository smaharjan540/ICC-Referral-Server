package icc.referral.schedular;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import icc.referral.model.QueuedEmail;
import icc.referral.service.QueuedEmailService;
import icc.referral.utils.EmailUtils;

public class EmailSchedular {

	@Autowired
	QueuedEmailService emailService;

	@Scheduled(cron = "0 30 * * * *")
	public void sendQueuedEmails() {
		Date date = Calendar.getInstance().getTime();
		DateFormat timeFormatter = DateFormat.getTimeInstance(DateFormat.DEFAULT);
		String currenttime = timeFormatter.format(date);
		System.out.println(currenttime + " : Running job " + this.getClass().getName());

		List<QueuedEmail> queuedEmails = emailService.findByStatus(EmailUtils.EMAIL_NOT_SENT);

		for (QueuedEmail email : queuedEmails) {
			try {
				if (EmailUtils.sendEmailNotification(email)) {
					email.setStatus(EmailUtils.EMAIL_SENT);
					email.setSentdate(new Date());

				}
			} catch (Exception e) {
				email.setStatus(EmailUtils.EMAIL_SENT_FAILED);
				e.printStackTrace();
			}
			emailService.addEmail(email);
		}

	}

}
