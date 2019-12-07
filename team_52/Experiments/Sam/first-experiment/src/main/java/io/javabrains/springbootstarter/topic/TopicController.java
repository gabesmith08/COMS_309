package io.javabrains.springbootstarter.topic;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class TopicController {
	
	@Autowired
	private TopicService topicService; //this is our TopicService instance
	
	//Get requests use this because GET is the default
	@RequestMapping("/topics")
	public List<topic> getAllTopics()
	{
		return topicService.getAllTopics();
	}
	//Get with the longer version
	@RequestMapping(method=RequestMethod.GET , value="/topics/{id}")// curly braces annotate variable
	public topic getTopic(@PathVariable String id) //@PathVariable denotes that the parameter comes from the uri
	{
		return topicService.getTopic(id);
	}
	
	//trying to use a post request
	//must specify POST method, but don't have uri
	//need to add VALUE method
	//the body is what is sent in the POST request
	//want to take what is in the body, convert to a topic instance, then add it to the list in TopicService!!
	@RequestMapping(method=RequestMethod.POST, value="/topics")
	public void addTopic(@RequestBody topic topic) //the REQUESTBODY takes what is in the method and sends it with POST(JSON representation)
	{
		topicService.addTopic(topic);
	}
	
	//Put requests
	@RequestMapping(method=RequestMethod.PUT, value="/topics/{id}")
	public void updateTopic(@RequestBody topic topic, @PathVariable String id)
	{
		topicService.updateTopic(topic, id);
	}
	
	//Delete Requests
	@RequestMapping(method=RequestMethod.DELETE , value="/topics/{id}")// curly braces annotate variable
	public void deleteTopic(@PathVariable String id) //@PathVariable denotes that the parameter comes from the uri
	{
		topicService.deleteTopic(id);
	}
}


