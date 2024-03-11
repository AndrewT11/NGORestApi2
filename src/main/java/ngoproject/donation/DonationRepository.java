package ngoproject.donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface DonationRepository extends JpaRepository<Donation, Integer> {

}
