package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import error.ErrorHandler;

@Controller
// change to rest controller
public class Error extends ErrorHandler {

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String handle400() {

		return "redirect:/";
	}
	
	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String handle404() {

		return "redirect:/";
	}
}
