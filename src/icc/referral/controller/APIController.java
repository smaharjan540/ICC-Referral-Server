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

		List<Referral> referrals = referralService.getAllReferralsByRecruiterId(recruiterid).stream()
				.map(r -> r.updateReferral(r)).collect(Collectors.toList());

		if (referrals.isEmpty()) {
			return new ResponseEntity<List<Referral>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Referral>>(referrals, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/recruiter/referral", method = RequestMethod.PUT)
	public ResponseEntity<Referral> updateReferral(@RequestBody Referral referral) {
		System.out.println("Updating Referral " + referral.getId());

		referralService.requestUpdateReferral(referral);

		return new ResponseEntity<Referral>(referral, HttpStatus.OK);
	}

}
