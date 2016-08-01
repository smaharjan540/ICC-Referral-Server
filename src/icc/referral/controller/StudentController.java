package icc.referral.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import icc.referral.model.Answer;
import icc.referral.model.Course;
import icc.referral.model.Question;
import icc.referral.model.Resource;
import icc.referral.model.Student;
import icc.referral.model.User;
import icc.referral.service.AnswerService;
import icc.referral.service.QuestionService;
import icc.referral.service.ResourceService;
import icc.referral.service.UserService;
import icc.referral.utils.FileMeta;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private UserService userService;
	@Autowired
	ServletContext context;
	@Autowired
	private QuestionService questionService;

	@Autowired
	private AnswerService answerService;

	@RequestMapping(value = "/discussion", method = RequestMethod.GET)
	public String questionPage(ModelMap model, HttpSession session) {
		Student student = (Student) userService.findByUsername((String) session.getAttribute("username"));
		model.addAttribute("question", new Question());
		model.addAttribute("courses", getFacultiesMap(student.getCourses()));
		return "studentdiscussion";
	}

	@RequestMapping(value = "/discussion/{id}", method = RequestMethod.GET)
	public String questionPageWrtCourse(@PathVariable long id, ModelMap model, HttpSession session) {
		Student student = (Student) userService.findByUsername((String) session.getAttribute("username"));
		model.addAttribute("questions", questionService.findByCourse(id));
		model.addAttribute("question", new Question());
		model.addAttribute("courses", getFacultiesMap(student.getCourses()));
		return "studentdiscussion";
	}

	@RequestMapping(value = "/discussion/question", method = RequestMethod.POST)
	public String addQuestion(Question question, HttpSession session) {

		User user = userService.findByUsername((String) session.getAttribute("username"));
		question.setUser(user);
		question.setPostDate(new Date());
		questionService.addQuestion(question);

		return "redirect:/student/discussion";
	}

	/*
	 * @RequestMapping(value = "/discussion/answer/{id}", method =
	 * RequestMethod.GET) public String getAnswerPage(@PathVariable long id,
	 * Model model) { Answer answer = new Answer(); model.addAttribute("answer",
	 * answer); return "studentanswer"; }
	 */

	@RequestMapping(value = "/discussion/answer", method = RequestMethod.GET)
	public String getAnswerPage(@RequestParam("q") Long id, Model model) {
		model.addAttribute("answer", new Answer());
		return "studentanswer";
	}

	@RequestMapping(value = "/discussion/answer", method = RequestMethod.POST)
	public String adding(@RequestParam("q") long id, Answer answer, HttpSession session) {
		User user = userService.findByUsername((String) session.getAttribute("username"));
		answer.setAnswerDate(new Date());
		Question question = questionService.getQuestionById(id);

		Answer result = answerService.addAnswer(answer);

		updateAnswerResult(user.getId(), question.getId(), result.getId());

		return "redirect:/student/discussion";
	}

	public void updateAnswerResult(long user_id, long question_id, long id) {

		answerService.updateAnswerResult(user_id, question_id, id);

	}

	/*
	 * @RequestMapping(value = "/discussion/answer/{id}", method =
	 * RequestMethod.POST) public String addAnswer(@PathVariable long id, Answer
	 * answer, HttpSession session) { System.out.println("Answer.id :::"
	 * +answer.getId()); Question question =
	 * questionService.getQuestionById(id);
	 * System.out.println(question.getId()); User user =
	 * userService.findByUsername((String) session.getAttribute("username"));
	 * answer.setUser(user); answer.setAnswerDate(new Date());
	 * answer.setQuestion(question);
	 * 
	 * question.addAnswer(answer);
	 * 
	 * answerService.addAnswer(answer); return "redirect:/student/discussion"; }
	 */

	@RequestMapping(value = "/resource", method = RequestMethod.GET)
	public String resourcePage(ModelMap model, HttpSession session) {
		Student student = (Student) userService.findByUsername((String) session.getAttribute("username"));

		model.addAttribute("resource", new Resource());
		model.addAttribute("courses", getFacultiesMap(student.getCourses()));
		return "studentresource";
	}

	@RequestMapping(value = "/resource/{id}", method = RequestMethod.POST)
	public String addFaculty(@PathVariable long id, Resource resource, HttpSession session) {

		resource.setPostedDate(new Date());
		resource.setUser(userService.findByUsername((String) session.getAttribute("username")));

		resourceService.addResource(resource);

		return "redirect:/student/resource/" + id;
	}

	private Map<Long, String> getFacultiesMap(Set<Course> courses) {
		Map<Long, String> faculties = new LinkedHashMap<>();

		for (Course course : courses) {
			faculties.put(course.getId(), course.getName());
		}

		return faculties;

	}

	@RequestMapping(value = "/resource/{id}", method = RequestMethod.GET)
	public String resourcePage(@PathVariable long id, ModelMap model, HttpSession session) {
		Student student = (Student) userService.findByUsername((String) session.getAttribute("username"));
		model.addAttribute("resources", resourceService.findByCourse(id));
		model.addAttribute("resource", new Resource());
		model.addAttribute("courses", getFacultiesMap(student.getCourses()));
		return "studentresource";
	}

	/*
	 * @RequestMapping(value = "/resource/{id}", method = RequestMethod.GET)
	 * public String resourcePage(@PathVariable long id, ModelMap model,
	 * HttpSession session) { String username = (String)
	 * session.getAttribute("username");
	 * 
	 * model.addAttribute("resources", resourceService.findByCourse(id));
	 * model.addAttribute("resource", new Resource());
	 * model.addAttribute("coursesMap",
	 * courseService.findAllCoursesByStudent(username)); return
	 * "studentresource"; }
	 */

	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public String messagePage(ModelMap model) {
		return "studentmessage";
	}

	@RequestMapping(value = { "profile/upload", "resource/profile/upload" }, method = RequestMethod.POST)
	public @ResponseBody LinkedList<FileMeta> uploading(MultipartHttpServletRequest request,
			HttpServletResponse response) {

		LinkedList<FileMeta> files = new LinkedList<FileMeta>();
		FileMeta fileMeta = null;

		String FILE_URL = context.getRealPath("") + "resources/";

		/*
		 * String officialEmail ="SDS";// request.getParameter("officialEmail");
		 * if (StringUtils.isNotEmpty(officialEmail) &&
		 * officialEmail.contains("@")) { officialEmail =
		 * officialEmail.substring(0, officialEmail.indexOf('@')); }
		 */

		// 1. build an iterator
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;

		// 2. get each file
		while (itr.hasNext()) {

			// 2.1 get next MultipartFile
			mpf = request.getFile(itr.next());
			System.out.println(mpf.getOriginalFilename() + " uploaded! " + files.size());

			// 2.2 if files > 10 remove the first from the list
			if (files.size() >= 10)
				files.pop();

			// 2.3 create new fileMeta
			fileMeta = new FileMeta();
			fileMeta.setFileName(mpf.getOriginalFilename());
			fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
			fileMeta.setFileType(mpf.getContentType());
			fileMeta.setFileURL(FILE_URL + mpf.getOriginalFilename());

			try {
				fileMeta.setBytes(mpf.getBytes());

				File originalFile = new File(FILE_URL + mpf.getOriginalFilename());
				System.out.println("FILE_URL : " + FILE_URL);
				/*
				 * File renamedFile = new File(FILE_URL + officialEmail + "_" +
				 * mpf.getOriginalFilename()); if
				 * (originalFile.renameTo(renamedFile)) {
				 * System.out.println("Renamed"); } else { System.out.println(
				 * "Renaming failed"); }
				 */

				// copy file to local disk (make sure the path
				// "e.g. D:/temp/files" exists)
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(originalFile));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 2.4 add to files
			files.add(fileMeta);

		}

		// result will be like this
		// [{"fileName":"app_engine-85x77.png","fileSize":"8
		// Kb","fileType":"image/png"},...]
		return files;

	}

}
