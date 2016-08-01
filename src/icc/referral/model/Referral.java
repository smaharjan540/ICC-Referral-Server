package icc.referral.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity(name = "referral")
public class Referral {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String skill;
	@Transient
	private List<String> skills;
	private String connection;
	private String status;
	private String extra;
	private String email;
	private String phone;
	private String linkedin;
	private String github;
	private String twitter;
	private String other;
	@Temporal(TemporalType.DATE)
	private Date createdate;
	@Transient
	private String username;
	@ManyToOne
	private User user;
	private String recruiterstatus;
	private String notification;

	private String resumefile;
	@Transient
	private String timestamp;
	@Transient
	private String referredby;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public String getResumefile() {
		return resumefile;
	}

	public void setResumefile(String resumefile) {
		this.resumefile = resumefile;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public String getRecruiterstatus() {
		return recruiterstatus;
	}

	public void setRecruiterstatus(String recruiterstatus) {
		this.recruiterstatus = recruiterstatus;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getReferredby() {
		return referredby;
	}

	public void setReferredby(String referredby) {
		this.referredby = referredby;
	}

	public Referral updateReferral(Referral referral) {
		referral.setTimestamp(
				(new Date().getTime() - referral.getCreatedate().getTime()) / (1000 * 60 * 60 * 24) + " Days old");
		referral.setReferredby(referral.getUser().getFirstname() + " " + referral.getUser().getLastname());
		return referral;
	}

}
