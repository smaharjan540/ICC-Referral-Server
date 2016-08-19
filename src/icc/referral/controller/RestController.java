package icc.referral.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import icc.referral.model.User;

/**
 * @author Nagesh.Chauhan
 *
 */
@Controller
@RequestMapping("/api/users")
public class RestController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<User> list() {
		System.out.println("hait lado...asdfads..");
		List<User> users = new ArrayList<>();
		users.add(new User("lado", "lado"));
		users.add(new User("lado1", "lado1"));
		users.add(new User("lado2", "lado2"));
		return users;

	}
}
