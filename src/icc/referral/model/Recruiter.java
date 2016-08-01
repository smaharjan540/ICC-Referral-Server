package icc.referral.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RECRUITER")
public class Recruiter extends User {

}
