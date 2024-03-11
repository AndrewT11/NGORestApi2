package ngoproject.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ngoproject.model.LoginRequest;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/user")
class UserRestController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public void registerUser(@RequestBody User user) {
		userService.saveUser(user);
	}
	
	@PostMapping("/login")
	public User login(@RequestBody LoginRequest loginRequest) {
		String email = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		Optional<User> optional = userService.getUserByEmailAndPassword(email, password);
		
		if(optional.isPresent() ) {
			
			User user = optional.get();
			return user;
			
		} else {
			return null;
		}
	}
	
	@GetMapping("/getUserById/{id}")
	public User getUserById(@PathVariable int id) {
		return userService.getUserById(id);
	}
	
	
	@GetMapping("/getAllUsers")
	public List<User> getUsers() {
		return userService.getAllUsers();
	}
	
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<HttpStatus> updateUser(@PathVariable int id, @RequestBody User uUser) {
		uUser.setUserId(id);
		String updateMessage = userService.updateUser(uUser);
		if(updateMessage.contentEquals("Success")) {
			return ResponseEntity.ok(HttpStatus.OK);
		}
		
		return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("deleteUser/{id}")
	ResponseEntity<HttpStatus> deleteUser(@PathVariable Integer id) {
		String updateMessage = userService.deleteUser(id);
		if(updateMessage.contentEquals("Success")) {
			return ResponseEntity.ok(HttpStatus.OK);
		}
		
		return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
	}
	
	
}
