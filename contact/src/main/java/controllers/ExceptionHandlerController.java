package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.google.common.base.Joiner;


@Controller
public class ExceptionHandlerController {

	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;
	
	@RequestMapping(value = { "/f" }, method = RequestMethod.GET)
	public String addCustomerProfile(@RequestParam("p") String p) {
		
		List<HandlerMethod> m = requestMappingHandlerMapping.getHandlerMethodsForMappingName(p);
		@SuppressWarnings("unused")
		Set<Entry<RequestMappingInfo, HandlerMethod>> m2 = requestMappingHandlerMapping.getHandlerMethods().entrySet();
		
		//for(Entry<RequestMappingInfo, HandlerMethod> e : m2)
			//if(e.getKey().getPatternsCondition().getPatterns().toArray()[0]);
		
		return "redirect:checkPage";
	}
}