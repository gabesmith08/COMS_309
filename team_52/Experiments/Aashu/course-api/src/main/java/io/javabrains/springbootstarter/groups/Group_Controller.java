package io.javabrains.springbootstarter.groups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.javabrains.springbootstarter.topic.Topic;

public class Group_Controller {
	@RestController
	public class GroupController {
		
		
		@RequestMapping ("/groups")
		public List<Group_Model> getAllGroups() {
			
				List<Group_Model> list = new ArrayList<>();
				return list;
				
			}
	}
	
}
