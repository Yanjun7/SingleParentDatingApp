package com.team9.spda_team9.ml;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@Async
	@PostMapping("/checkSA/{id}")
	public CompletableFuture<String> invokeSentimentalAnalysis(@PathVariable("id") String userId) { //argument should be userId
		String link = "http://127.0.0.1:5000/model1?x=" + userId;
//		String input = "12";
		HttpURLConnection conn = null;
//		DataOutputStream os = null;
		String output = null;
		
		try {
//			byte[] postData = input.getBytes(StandardCharsets.UTF_8);
			URL url = new URL(link);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("charset", "utf-8");
//            conn.setRequestProperty("Content-Length", Integer.toString(input.length()));
            
//            os = new DataOutputStream(conn.getOutputStream());
//            os.write(postData);
//            os.flush();
			
            if (conn.getResponseCode() != 200) {
            	System.out.print("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println("result for user" + userId + " = " + output);
            }
            
            conn.disconnect();
            
		} catch (MalformedURLException e) { 
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.disconnect();
		}
		
		return CompletableFuture.completedFuture(output);
	}
}
