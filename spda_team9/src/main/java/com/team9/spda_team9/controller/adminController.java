package com.team9.spda_team9.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team9.spda_team9.user.UserService;

@Controller
@RequestMapping("/admin")
public class adminController {
	
	@Autowired
	UserService userService;
	
	 @RequestMapping(value = "/detail/{id}")
	    public String showSupplier(@PathVariable("id") String username, Model model) throws InterruptedException, ExecutionException {
		 model.addAttribute("user", userService.getUser(username));
	        return "Userdetail";
	    }
	 
	 @RequestMapping(value = "/detail/{id}")
	 public String suspenduser(@PathVariable("id") String username, Model model) throws InterruptedException, ExecutionException {
		 model.addAttribute("user", userService.getUser(username));
	        return "Userdetail";
	    }
	
	
	
	

}
