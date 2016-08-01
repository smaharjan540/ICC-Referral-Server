package icc.referral.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import icc.referral.service.FacultyService;
import icc.referral.service.UserService;

@Controller
@SessionAttributes("username")
public class LoginLogoutController {

	@Autowired
	UserService userService;
	@Autowired
	private FacultyService facultyService;

	@RequestMapping(value = "/admin/**", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		String username = getPrincipal();
		model.addAttribute("username", username);

		return "admin";
	}

	@RequestMapping(value = "/school/**", method = RequestMethod.GET)
	public String schoolAdminPage(ModelMap model) {

		String username = getPrincipal();
		model.addAttribute("username", username);

		return "schooladmin";
	}

	@RequestMapping(value = "/professor/**", method = RequestMethod.GET)
	public String professorAdminPage(ModelMap model) {

		String username = getPrincipal();
		model.addAttribute("username", username);

		return "professoradmin";
	}

	@RequestMapping(value = "/student/**", method = RequestMethod.GET)
	public String facultyAdminPage(ModelMap model) {

		String username = getPrincipal();
		model.addAttribute("username", username);

		return "studentpage";
	}

	@CrossOrigin
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response, SessionStatus status) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		status.setComplete();
		return "redirect:/login?logout";
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
}
