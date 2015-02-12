package controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;

import error.Log;

public class ErrorHandler {
	
	@ExceptionHandler(Throwable.class)
    public String handleThrowable(Throwable t) {
    			
		Log.logError(t);
        
		return "redirect:/";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Throwable t) {    	
		
		Log.logError(t);
		
        return "redirect:/";
    }
}
