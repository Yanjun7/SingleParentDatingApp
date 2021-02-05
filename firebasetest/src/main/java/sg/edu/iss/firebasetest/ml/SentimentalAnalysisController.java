package sg.edu.iss.firebasetest.ml;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.firebasetest.forum.Forum;
import sg.edu.iss.firebasetest.forum.ForumService;

@RestController
public class SentimentalAnalysisController {
	
	@Autowired
	private ForumService forumService;
	
	@GetMapping("/getTextByUserId")
	public Forum getUser(@RequestHeader() String userId) throws InterruptedException, ExecutionException {
		return forumService.getForum(userId);
	}
}
