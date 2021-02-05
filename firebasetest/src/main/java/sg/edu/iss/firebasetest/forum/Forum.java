package sg.edu.iss.firebasetest.forum;

import java.util.List;

public class Forum {
	private String name;
	private List<Topic> topics;
	
	public Forum () {}
	
	public Forum(String name, List<Topic> topics) {
		this.name = name;
		this.topics = topics;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
	
}
