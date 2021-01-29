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
public class ForumController {

	@Autowired
	private ForumService forumService;
	
	@GetMapping("/getForum")
	public Forum getUser(@RequestHeader() String name) throws InterruptedException, ExecutionException {
		return forumService.getForum(name);
	}
	
	@PostMapping("/createForum")
	public String createUser(@RequestBody Forum forum) throws InterruptedException, ExecutionException {
		return forumService.saveForum(forum);
	}
	
	@PutMapping("/updateForum")
	public String updateUser(@RequestBody Forum forum) throws InterruptedException, ExecutionException {
		return forumService.updateForum(forum);
	}
	
	@DeleteMapping("/deleteForum")
	public String deleteUser(@RequestHeader() String name) throws InterruptedException, ExecutionException {
		return forumService.deleteForum(name);
	}
}
