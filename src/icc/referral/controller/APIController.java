package icc.referral.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.fabric.xmlrpc.base.Array;

import icc.referral.model.Consultant;
import icc.referral.model.Referral;
import icc.referral.model.User;
import icc.referral.service.ReferralService;
import icc.referral.service.UserService;

@RestController
@RequestMapping(value = "/api")
public class APIController {

	@Autowired
	UserService userService;

	@Autowired
	private ReferralService referralService;

	@CrossOrigin
	@RequestMapping(value = "/recruiters", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getRectuiters() {

		List<String> recruiters = userService.findAllRecruiterUsers().stream().map(u -> u.getUsername())
				.collect(Collectors.toList());

		if (recruiters.isEmpty()) {
			return new ResponseEntity<List<String>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<String>>(recruiters, HttpStatus.OK);

	}

	@CrossOrigin
	@RequestMapping(value = "/consultant/add", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> createConsultantUser(@RequestBody Consultant consultant) {
		System.out.println("Creating Consultant " + consultant.getName());

		User user = userService.findByUsername(consultant.getRecruiter());
		consultant.setRecruiter_id(user.getId());

		userService.addUser(consultant);

		return new ResponseEntity<String>("Added Successfully!", HttpStatus.CREATED);
	}

	@CrossOrigin
	@RequestMapping(value = "/recruiter/referral/{recruiterid}", method = RequestMethod.GET)
	public ResponseEntity<List<Referral>> getRecruiterReferrals(@PathVariable("recruiterid") long recruiterid) {

		List<Referral> referrals = referralService.getAllReferralsByRecruiterId(recruiterid).stream().map(r->r.updateReferral(r)).collect(Collectors.toList());

//		List<ReferralPOJO> results = new ArrayList<>();
//		ReferralPOJO result = null;
//		for(Referral referral: referrals){
//			result = new ReferralPOJO();
//			result.setId(referral.getId());
//			result.setName(referral.getName());
//			result.setSkill(referral.getSkill());
//			result.setConnection(referral.getConnection());
//			result.setStatus(referral.getStatus());
//			result.setExtra(referral.getExtra());
//			result.setEmail(referral.getEmail());
//			result.setPhone(referral.getPhone());
//			result.setLinkedin(referral.getLinkedin());
//			result.setGithub(referral.getGithub());
//			result.setTwitter(referral.getTwitter());
//			result.setOther(referral.getOther());
//			result.setTimestamp((new Date().getTime()-referral.getCreatedate().getTime())/ (1000 * 60 * 60 * 24)+" days passed");
//			result.setReferredby(referral.getUser().getFirstname()+" "+ referral.getUser().getLastname()+"-"+referral.getUser().getEmail());		
//			results.add(result);
//		}

		if (referrals.isEmpty()) {
			return new ResponseEntity<List<Referral>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Referral>>(referrals, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/recruiter/referral", method = RequestMethod.PUT)
	public ResponseEntity<Referral> updateReferral(@RequestBody Referral referral) {
		System.out.println("Updating Referral " + referral.getId());

		referralService.addReferral(referral);

		return new ResponseEntity<Referral>(referral, HttpStatus.OK);
	}

//	class ReferralPOJO {
//
//		private long id;
//		private String name;
//		private String skill;
//		private String connection;
//		private String status;
//		private String extra;
//		private String email;
//		private String phone;
//		private String linkedin;
//		private String github;
//		private String twitter;
//		private String other;
//		private String timestamp;
//		private String referredby;
//
//		public Long getId() {
//			return id;
//		}
//
//		public void setId(Long id) {
//			this.id = id;
//		}
//
//		public String getName() {
//			return name;
//		}
//
//		public void setName(String name) {
//			this.name = name;
//		}
//
//		public String getSkill() {
//			return skill;
//		}
//
//		public void setSkill(String skill) {
//			this.skill = skill;
//		}
//
//		public String getConnection() {
//			return connection;
//		}
//
//		public void setConnection(String connection) {
//			this.connection = connection;
//		}
//
//		public String getStatus() {
//			return status;
//		}
//
//		public void setStatus(String status) {
//			this.status = status;
//		}
//
//		public String getExtra() {
//			return extra;
//		}
//
//		public void setExtra(String extra) {
//			this.extra = extra;
//		}
//
//		public String getEmail() {
//			return email;
//		}
//
//		public void setEmail(String email) {
//			this.email = email;
//		}
//
//		public String getPhone() {
//			return phone;
//		}
//
//		public void setPhone(String phone) {
//			this.phone = phone;
//		}
//
//		public String getLinkedin() {
//			return linkedin;
//		}
//
//		public void setLinkedin(String linkedin) {
//			this.linkedin = linkedin;
//		}
//
//		public String getGithub() {
//			return github;
//		}
//
//		public void setGithub(String github) {
//			this.github = github;
//		}
//
//		public String getTwitter() {
//			return twitter;
//		}
//
//		public void setTwitter(String twitter) {
//			this.twitter = twitter;
//		}
//
//		public String getOther() {
//			return other;
//		}
//
//		public void setOther(String other) {
//			this.other = other;
//		}
//
//		public String getTimestamp() {
//			return timestamp;
//		}
//
//		public void setTimestamp(String timestamp) {
//			this.timestamp = timestamp;
//		}
//
//		public String getReferredby() {
//			return referredby;
//		}
//
//		public void setReferredby(String referredby) {
//			this.referredby = referredby;
//		}
//
//	}

}
