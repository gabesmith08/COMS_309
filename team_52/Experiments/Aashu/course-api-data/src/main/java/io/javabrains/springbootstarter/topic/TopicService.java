package io.javabrains.springbootstarter.topic;
//going to be a business service
//other class depends on this service


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
	
	@Autowired
	private TopicRepository topicRepository;
	
	public List<Topic> getAllTopics()
	{
		List<Topic> topics= new ArrayList<>();
		topicRepository.findAll()
		.forEach(topics::add);
		
		return topics;
	}
	
	public Topic GetTopic(String id)
	{
		return topicRepository.findby;
	}
	
	public void addTopic(Topic topic)
	{
		topicRepository.save(topic);
	}
	
	public void updateTopic(Topic topic)
	{
		topicRepository.save(topic);
	}
	
	public void deleteTopic(String id)
	{
		topicRepository.delete(id);
	}
	
}