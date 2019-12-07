package io.javabrains.springbootstarter.topic;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {
	
	@Autowired
	private TopicService topicService;
	
	@RequestMapping("/topics")//Get request mapped to URI topics
	public List<Topic> getAllTopics() {
		//going to connect to the database and then return all the data in json format
		return topicService.getAllTopics();
	}
	
	@RequestMapping("/topics/{id}")
	public Topic getTopic(@PathVariable String id) {
		return topicService.getTopic(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/topics") //Post Request for making new things
	public void  addTopic(@RequestBody Topic topic)//going to get an instance of topic in json
	{
			topicService.addTopic(topic);
	}
	

	@RequestMapping(method=RequestMethod.PUT, value = "/topics/{id}") //Post Request for making new things
	public void  updateTopic(@RequestBody Topic topic, @PathVariable String id)//going to get an instance of topic in json
	{
			//topicService.updateTopic(id,topic);
	}
	
}
