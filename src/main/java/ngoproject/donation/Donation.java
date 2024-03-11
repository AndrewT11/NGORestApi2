package ngoproject.donation;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="donation")
@Getter
@Setter
class Donation {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private Integer eventId;
	private String eventName;
	private Integer userId;
	private Integer amount;
	private Date donationDate;
	private boolean reocurring;
	private String firstName;
	private String lastName;
	private String phone;
	private String address;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	private String email;
}
