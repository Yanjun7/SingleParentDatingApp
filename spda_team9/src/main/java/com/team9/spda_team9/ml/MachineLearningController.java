package com.team9.spda_team9.ml;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.team9.spda_team9.comment.CommentService;
import com.team9.spda_team9.comment.ICommentService;
import com.team9.spda_team9.topic.ForumService;
import com.team9.spda_team9.topic.IForumService;

@RestController
public class MachineLearningController {
	
	@Autowired
	private IForumService forumService;
	
	@Autowired
	private ICommentService commentService;
	
	@Autowired
	public MachineLearningController(ForumService forumService, CommentService commentService) {
		this.forumService = forumService;
		this.commentService = commentService;
	}
	
	@GetMapping("/getTextByUserId")
	public List<String> getTextByUserId(@RequestHeader() String userId) throws InterruptedException, ExecutionException {
		return commentService.getCommentsByUserId(userId);
	}
}
