package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Post {

	@RequestMapping(value = {"/error"}, method = RequestMethod.GET)
	public String getPost(){

		return "Post";
	}
	
	@RequestMapping(value = {"/post"}, method = RequestMethod.POST)
	@ResponseBody
	public String post(@RequestParam("lastName") String p){		
		
		return p + " " + 10;
	}
}
