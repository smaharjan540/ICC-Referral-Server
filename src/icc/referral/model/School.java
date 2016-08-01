package icc.referral.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;

@Entity
@DiscriminatorValue("SCHOOL")
@SecondaryTables(@SecondaryTable(name = "school", pkJoinColumns = {
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id") }))
public class School extends User {

	@Column(table = "school")
	private String schoolname;
	@Column(table = "school")
	private String websiteurl;
	@Column(table = "school")
	private String logourl;

	public School() {

	}

	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

	public String getLogourl() {
		return logourl;
	}

	public void setLogourl(String logourl) {
		this.logourl = logourl;
	}

	public String getWebsiteurl() {
		return websiteurl;
	}

	public void setWebsiteurl(String websiteurl) {
		this.websiteurl = websiteurl;
	}

}
