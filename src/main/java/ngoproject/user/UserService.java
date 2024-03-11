package ngoproject.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	Optional<User> getUserByEmailAndPassword(String email, String password) {
		return Optional.ofNullable(userRepository.findByEmailAndPassword(email, password));
	}
	
	public User getUserById(Integer id) {
		Optional<User> optional = this.userRepository.findById(id);
		
		if(optional.isPresent()) {
			User user = optional.get();
			return user;
		} else {
			throw new RuntimeException("User with this ID does not exist, " + id);
		}
	}
	
	String saveUser(User user) {
		userRepository.save(user);
		return "User saved succesfully";
	}
	
	List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	String updateUser(User uUser) {
		Optional<User> optional = userRepository.findById(uUser.getUserId());
		
		if(optional.isPresent()) {
			
			userRepository.save(uUser);
			
			return "Success";
		} 
		return "Bad Request";
	}
	
	String deleteUser(Integer id) {
		Optional<User> optional = userRepository.findById(id);
		
		if(optional.isPresent()) {
			
			userRepository.deleteById(id);
			return "Success";
		} else {
			throw new RuntimeException("BE Delete User Fail ID: " + id);
		}
	}
}
