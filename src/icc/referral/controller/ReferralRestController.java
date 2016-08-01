package icc.referral.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.UriComponentsBuilder;

import com.mysql.fabric.xmlrpc.base.Array;

import icc.referral.model.Referral;
import icc.referral.model.User;
import icc.referral.service.ReferralService;
import icc.referral.service.UserService;
import icc.referral.utils.FileMeta;

@RestController
@RequestMapping(value = "/api/referral")
public class ReferralRestController {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	private UserService userService;

	@Autowired
	private ReferralService referralService;

	@CrossOrigin
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Referral>> listAllReferralss(HttpSession session) {
		
		System.out.println((String) session.getAttribute("username"));
		User user = userService.findByUsername((String) session.getAttribute("username"));

		List<Referral> referrals = referralService.getAllReferral();
		System.out.println(referrals.get(0).getCreatedate());
		if (referrals.isEmpty()) {
			return new ResponseEntity<List<Referral>>(HttpStatus.NO_CONTENT);// You
																				// many
																				// decide
																				// to
																				// return
																				// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Referral>>(referrals, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Referral> getReferral(@PathVariable long id) {
		Referral referral = referralService.getReferralById(id);
		System.out.println(referral.getCreatedate());
		return new ResponseEntity<Referral>(referral, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/resources/{filename}", method = RequestMethod.GET)
	public ResponseEntity<File> getResource(@PathVariable String filename) {
		System.out.println("lado...");
		return new ResponseEntity<File>(new File(filename), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/skills", method = RequestMethod.GET)
	public ResponseEntity<List<String>> listAllSkillsSet() {
		List<String> skills = Arrays.asList("CSS3", "HTML5", "JavaScript", "Ruby on Rails", "Java", ".Net", "AngularJS",
				"React", "Spring", "Hibernate", "Jquery", "Ajax", "JSF", "JSP", "Scala");

		System.out.println("Skill size:" + skills.size());

		return new ResponseEntity<List<String>>(skills, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> createUser(@RequestBody Referral referral, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Referral " + referral.getName());
		System.out.println("referral.getUsername():"+referral.getUsername());
		User user = userService.findByUsername(referral.getUsername());
		System.out.println("user:"+user);
		referral.setUser(user);
		referral.setCreatedate(new Date());
		referral.setSkill(referral.getSkills().stream().collect(Collectors.joining(", ")));

		/*
		 * if (userService.isUserExist(user)) { System.out.println(
		 * "A User with name " + user.getName() + " already exist"); return new
		 * ResponseEntity<Void>(HttpStatus.CONFLICT); }
		 */

		referralService.addReferral(referral);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(referral.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@CrossOrigin
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Referral> deleteReferral(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting Referral with id " + id);

		Referral referral = referralService.getReferralById(id);
		if (referral == null) {
			System.out.println("Unable to delete. Referral with id " + id + " not found");
			return new ResponseEntity<Referral>(HttpStatus.NOT_FOUND);
		}

		referralService.delete(id);
		return new ResponseEntity<Referral>(HttpStatus.NO_CONTENT);
	}

	@CrossOrigin
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	
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
	
	
    public @ResponseBody String handleFileUpload( 
            @RequestParam("file") MultipartFile file){
            String name = "test11";
            System.out.println(name+file);
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = 
                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + " into " + name + "-uploaded !";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }

}
