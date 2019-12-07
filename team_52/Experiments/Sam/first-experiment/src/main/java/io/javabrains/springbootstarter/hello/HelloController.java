package io.javabrains.springbootstarter.hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController // can have methods that map to certain urls
public class HelloController {
	
	//this type is a GET http request
	@RequestMapping("/hello")
	public String sayhi()
	{
		return "Hi:)";
	}

}
