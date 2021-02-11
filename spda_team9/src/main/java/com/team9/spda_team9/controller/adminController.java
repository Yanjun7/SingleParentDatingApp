package com.team9.spda_team9.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team9.spda_team9.user.User;
import com.team9.spda_team9.user.UserService;

@Controller
@RequestMapping("/admin")
public class adminController {
	
	@Autowired
	UserService userService;
	
	 @RequestMapping(value = "/detail/{id}")
	    public String showSupplier(@PathVariable("id") String username, Model model) throws InterruptedException, ExecutionException {
		 User user=userService.getUser(username);
		 if(user.suspended==false) {
			 model.addAttribute("suspendTo","suspend");
		 }
		 else {
			 model.addAttribute("suspendTo","unsuspend");
		 }
		 
		 model.addAttribute("user", user);
	        return "Userdetail";
	    }
	 
	 @RequestMapping(value = "/suspend/{id}")
	 public String suspenduser(@PathVariable("id") String username,Model model) throws InterruptedException, ExecutionException {
		 User user=userService.getUser(username);
		 user.setSuspended(true);
		 userService.updateUser(user);
		 return "succedsuspend";
	    }
	 @RequestMapping(value = "/unsuspend/{id}")
	 public String unsuspenduser(@PathVariable("id") String username,Model model) throws InterruptedException, ExecutionException {
		 User user=userService.getUser(username);
		 user.setSuspended(false);
		 userService.updateUser(user);
		 return "succedsuspend";
	    }
	 
	 
	
	
	
	

}
