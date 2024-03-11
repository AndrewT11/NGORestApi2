package ngoproject.donation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class DonationService {

	@Autowired
	private DonationRepository donationRepository;
	
	List<Donation> getAllDonaltions() {
		return donationRepository.findAll();
	}
	
	void registerDonation(Donation donation) {
		donationRepository.save(donation);

	}
	
	String deleteDonation(Integer id) {
		Optional<Donation> optional = donationRepository.findById(id);
		
		if(optional.isPresent()) {
			Donation donation = optional.get();
			donationRepository.delete(donation);
			return "Donation deleted successfully";
		} else {
			throw new RuntimeException("BE Delete Donation Fail ID: "+ id);
		}
	} 
}
