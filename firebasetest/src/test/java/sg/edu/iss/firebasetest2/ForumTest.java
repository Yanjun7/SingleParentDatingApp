package sg.edu.iss.firebasetest2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.iss.firebasetest.forum.Forum;
import sg.edu.iss.firebasetest.forum.ForumService;
import sg.edu.iss.firebasetest.forum.Topic;

@SpringBootTest
class ForumTest {

	@Autowired
	private ForumService forumService;
	
	@Test
	void contextLoads() {
		
		List<Topic> topics = new ArrayList<>();
		topics.add(new Topic("Discipline 3yo", "My daughter is so naughty..."));
		topics.add(new Topic("How to make them listen?", "My sons 8 and 10 is very rebellious"));
		topics.add(new Topic("loving or discipline?", "It's so hard to balance loving and disciplining my children!"));
		
		Forum f1 = new Forum("Parenting", topics);
		try {
			forumService.saveForum(f1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}