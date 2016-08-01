package icc.referral.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import icc.referral.model.Course;
import icc.referral.model.Faculty;
import icc.referral.model.Professor;
import icc.referral.model.User;
import icc.referral.service.CourseService;
import icc.referral.service.FacultyService;
import icc.referral.service.UserService;

@Controller
@RequestMapping(value = "/school")
public class SchoolController {

	@Autowired
	private FacultyService facultyService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/faculty", method = RequestMethod.GET)
	public String facultyPage(ModelMap model) {
		model.addAttribute("faculties", facultyService.getFaculties());
		model.addAttribute("faculty", new Faculty());
		return "faculty";
	}

	@RequestMapping(value = "/faculty", method = RequestMethod.POST)
	public String addFaculty(Faculty faculty) {

		facultyService.addFaculty(faculty);

		return "redirect:/school/faculty";
	}

	@RequestMapping(value = "/faculty/delete", method = RequestMethod.POST)
	public String deleteFaculty(@RequestParam("id") long id) {

		facultyService.delete(id);
		return "redirect:/school/faculty";
	}

	@RequestMapping(value = "/course", method = RequestMethod.GET)
	public String coursePage(ModelMap model) {
		model.addAttribute("courses", courseService.getCourses());
		model.addAttribute("course", new Course());
		model.addAttribute("facultiesmap", facultyService.getFacultiesMap());
		return "course";
	}

	@RequestMapping(value = "/course", method = RequestMethod.POST)
	// public @ResponseBody Course addCourse(Course course) {
	public String addCourse(Course course) {

		courseService.addCourse(course);

		return "redirect:/school/course";
	}

	@RequestMapping(value = "/course/jsoncourselist/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Course> getCourseList(@PathVariable long id) {
		if (id == 0) {
			return new ArrayList<>();
		}
		return courseService.getCourses(id);
	}

	@RequestMapping(value = "/course/delete", method = RequestMethod.POST)
	public String deleteCourse(@RequestParam("id") long id) {
		courseService.delete(id);
		return "redirect:/school/course";
	}

	@RequestMapping(value = "/professor", method = RequestMethod.GET)
	public String professorPage(ModelMap model) {
		model.addAttribute("professorUsers", userService.findAllProfessorUsers());
		model.addAttribute("professor", new Professor());
		model.addAttribute("facultiesmap", facultyService.getFacultiesMap());
		return "userprofessor";
	}

	@RequestMapping(value = "/professor/professor", method = RequestMethod.POST)
	public String addProfessor(Professor professorUser) {
		if (professorUser.getCoursesids() != null)
			for (Long id : professorUser.getCoursesids()) {
				System.out.println(id + "...hello..");
				professorUser.addCourse(courseService.findById(id));
			}
		System.out.println(professorUser.getCoursesids().size());
		userService.addUser(professorUser);
		return "redirect:/school/professor";
	}

	@RequestMapping(value = "/professor/activate", method = RequestMethod.POST)
	public String activateProfessor(@RequestParam("id") long id, @RequestParam("active") boolean active) {

		userService.activateUser(active, id);
		return "redirect:/school/professor";
	}

}
