package icc.referral.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("CONSULTANT")
@SecondaryTables(@SecondaryTable(name = "consultant", pkJoinColumns = {
		@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id") }))
public class Consultant extends User {

	@Column(table = "consultant")
	private Long recruiter_id;
	@Transient
	private String recruiter;

	public Long getRecruiter_id() {
		return recruiter_id;
	}

	public void setRecruiter_id(Long recruiter_id) {
		this.recruiter_id = recruiter_id;
	}

	public String getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(String recruiter) {
		this.recruiter = recruiter;
	}

}
