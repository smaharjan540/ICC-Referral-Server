package icc.referral.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import icc.referral.model.Referral;
import icc.referral.model.Role;
import icc.referral.model.User;
import icc.referral.service.UserService;

@RestController
public class LoginLogoutRestController {

	@Autowired
	private UserService userService;

	@CrossOrigin
	@RequestMapping(value = "/api/login", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Auth> credentialCheck(@RequestBody User user, HttpSession session) {
		Auth auth = null;
		System.out.println(user.getPassword());
		user = userService.findByUsernamePassword(user.getUsername(), user.getPassword());
		if (user != null) {
			user.getRoles().stream().forEach(System.out::println);
			boolean ROLE_CONSULTANT = false;
			boolean ROLE_RECRUITER = false;
			for (Role role : user.getRoles()) {
				if(role.getType().equals("CONSULTANT")){
					ROLE_CONSULTANT = true;
				}
				if(role.getType().equals("RECRUITER")){
					ROLE_RECRUITER = true;
				}
			}
			if (ROLE_CONSULTANT) {
				session.setAttribute("username", user.getUsername());
				auth = new Auth(user.getId(), user.getUsername(), "CONSULTANT");
				System.out.println((String) session.getAttribute("username"));
				return new ResponseEntity<Auth>(auth, HttpStatus.OK);
			}
			if (ROLE_RECRUITER) {
				session.setAttribute("username", user.getUsername());
				auth = new Auth(user.getId(), user.getUsername(), "RECRUITER");
				System.out.println((String) session.getAttribute("username"));
				return new ResponseEntity<Auth>(auth, HttpStatus.OK);
			}
		}
		auth = new Auth();
		auth.setMessage("Invalid username/password");
		auth.setValidationErrors(new ValidationErrors("Aha, server-side validation error", "Use another password"));

		return new ResponseEntity<Auth>(auth, HttpStatus.UNAUTHORIZED);

	}

	class ValidationErrors {
		private String username;
		private String password;

		public ValidationErrors(String username, String password) {
			this.username = username;
			this.password = password;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}

	class Auth {
		private String username;
		private String role;
		private Long id;
		private String message;
		private ValidationErrors validationErrors;

		public Auth() {

		}

		public Auth(Long id, String userame, String role) {
			this.id = id;
			this.username = userame;
			this.role = role;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public ValidationErrors getValidationErrors() {
			return validationErrors;
		}

		public void setValidationErrors(ValidationErrors validationErrors) {
			this.validationErrors = validationErrors;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

	}

}
