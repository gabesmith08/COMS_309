package io.javabrains.springbootstarter.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //request maps to a particular URL
public class HelloController {
	
	@RequestMapping("/hello")//works for GET method
	public String sayhi() {
		return "Alaadin Mudafuckaaa";
	}

}
