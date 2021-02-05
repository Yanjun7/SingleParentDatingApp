package com.team9.spda_team9.ml;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.team9.spda_team9.comment.CommentService;
import com.team9.spda_team9.comment.ICommentService;
import com.team9.spda_team9.topic.ForumService;
import com.team9.spda_team9.topic.IForumService;

@Controller
public class MachineLearningController {
	
	@Autowired
	private IForumService forumService;
	
	@Autowired
	public MachineLearningController(ForumService forumService) {
		this.forumService = forumService;
	}
	
	@Autowired
	private ICommentService commentService;
	
	@Autowired
	public MachineLearningController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@GetMapping("/getTextByUserId")
	public List<String> getTextByUserId(@RequestHeader() String userId) throws InterruptedException, ExecutionException {
		return commentService.getCommentsByUserId(userId);
	}
}
