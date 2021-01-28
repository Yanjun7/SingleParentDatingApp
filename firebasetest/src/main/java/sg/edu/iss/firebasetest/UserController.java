package sg.edu.iss.firebasetest;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/getUser")
	public User getUser(@RequestHeader() String username) throws InterruptedException, ExecutionException {
		return userService.getUser(username);
	}
	
	@PostMapping("/createUser")
	public String createUser(@RequestBody User user) throws InterruptedException, ExecutionException {
		return userService.saveUser(user);
	}
	
	@PutMapping("/updateUser")
	public String updateUser(@RequestBody User user) throws InterruptedException, ExecutionException {
		return userService.updateUser(user);
	}
	
	@DeleteMapping("/deleteUser")
	public String deleteUser(@RequestHeader() String username) throws InterruptedException, ExecutionException {
		return userService.deleteUser(username);
	}
}
