package ngoproject.donation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ngoproject.email.MailSenderService;
import ngoproject.user.User;
import ngoproject.user.UserService;

// email
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/donation")
public class DonationRestController {

	@Autowired
	private DonationService donationService;
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private UserService userService;
	@Autowired
	private MailSenderService mailService;
	
	
	
	@GetMapping("/donations")
	List<Donation> getAllDonations() {
		return donationService.getAllDonaltions();
	}

	
	@PostMapping("/register")
	void registerDonation(@RequestBody Donation donation) {
		donationService.registerDonation(donation);
		
		// Fetch user information based on userId
		User user = userService.getUserById(donation.getUserId());
		
		// Check if user exist and has an email
		if (user != null && user.getEmail() != null) {
			donation.setEmail(user.getEmail());
		}
		
		//EMAIL LOGIC
		String title = "From NGO Application, thank you for your donation!!!";
		
		String body = "Dear Mr/Mrs." + donation.getLastName() + ", \n \n"
				+ "On behalf of the NGO Application team, we humbly thank you for your $ " + donation.getAmount() + " donation. \n \n"
				+ "Please know that your contribution is going to a great cause and we are forever grateful. \n"
				+ "\n"
				+ "Sincerely, \n \n"
				+ "     NGO Application team."; 
		
		mailService.sendNewMail(donation.getEmail(), title, body);
	}
	
	@DeleteMapping("/delete/{id}")
	ResponseEntity<String> deleteDonation(@PathVariable Integer id) {
		String updateMessage = donationService.deleteDonation(id);
		return ResponseEntity.ok(updateMessage);
	}
	
//	@GetMapping("test-email")
//	public void foo() {
//		mailService.sendNewMail("tranandrew.tech@gmail.com", "Test email", "Test email body");
//	}
	

}
