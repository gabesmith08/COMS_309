package Team_52.team_52.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@RequestMapping("/Hello")	//only works when a Get Request received

	public String sayhi() {
		return "HI";
	}

}
