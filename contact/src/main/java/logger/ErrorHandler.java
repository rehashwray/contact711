package logger;

import org.springframework.web.bind.annotation.ExceptionHandler;

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
