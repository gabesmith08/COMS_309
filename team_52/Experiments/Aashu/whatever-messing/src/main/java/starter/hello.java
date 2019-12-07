package starter;

import org.springframework.web.bind.annotation.RestController;

@RestController

public class hello {
	public String sayhi() {
		return "Hi";
	}

}
