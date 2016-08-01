package icc.referral.aspect;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import icc.referral.model.QueuedEmail;
import icc.referral.model.Recruiter;
import icc.referral.model.Referral;
import icc.referral.model.User;
import icc.referral.service.QueuedEmailService;
import icc.referral.utils.EmailUtils;

@Aspect
public class EmailAspect {

	@Autowired
	private QueuedEmailService emailService;

	@After("execution(* icc.referral.service.ReferralServiceImpl.requestUpdateReferral(..))")
	public void createUpdateReferralEmailQueue(JoinPoint joinpoint) {
		System.out.println(
				"Running aspect : execution(* icc.referral.service.ReferralServiceImpl.requestUpdateReferral(..))");

		Object[] args = joinpoint.getArgs();
		Referral referral = (Referral) args[0];

		String name = referral.getUser().getFirstname();

		StringBuffer buffer = new StringBuffer();
		buffer.append("Hello " + name + ",\n");
		buffer.append("Is this candidate still available? Please let me know candidate is still available.\n\n");
		buffer.append("Thank you!");

		createEmailQueued(referral.getUser().getEmail(), "Request update", buffer.toString(), "Request update");

	}

	@After("execution(* icc.referral.service.UserServiceImpl.addUser(..))")
	public void createUserEmailQueue(JoinPoint joinpoint) {

		System.out.println("Running aspect : execution(* icc.referral.service.UserServiceImpl.addUser(..))");

		Object[] args = joinpoint.getArgs();
		User user = (User) args[0];

		if (user instanceof Recruiter) {
			user = (Recruiter) user;
		}

		StringBuffer buffer = new StringBuffer();
		buffer.append("Hello,\n");
		buffer.append("Your account has been created. Please login with the following credential in our website:\n\n");
		buffer.append("username:" + user.getUsername() + "\n");
		buffer.append("password:" + user.getPassword() + "\n\n");
		buffer.append("Thank you!");

		createEmailQueued(user.getEmail(), "User created successfully!", buffer.toString(),
				user.getClass().getSimpleName() + " user created");

	}

	private void createEmailQueued(String emailto, String subject, String message, String type) {
		QueuedEmail email = new QueuedEmail();
		email.setEmailto(emailto);
		email.setType(type);
		email.setEmailfrom("to.sujan.maharjan@gmail.com");
		email.setSubject(subject);

		email.setMessage(message);
		email.setStatus(EmailUtils.EMAIL_NOT_SENT);
		email.setCreateddate(new Date());

		emailService.addEmail(email);
	}

}
