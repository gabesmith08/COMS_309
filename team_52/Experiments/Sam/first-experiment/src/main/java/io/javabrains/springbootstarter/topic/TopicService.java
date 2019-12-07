package io.javabrains.springbootstarter.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Service;

@Service //marks as spring business service
public class TopicService {
	//needs to be an ArrayList because those are mutable
	private List<topic> topics = new ArrayList<>(Arrays.asList(
			new topic("spring", "Spring Framework", "Spring Framework Description."),
			new topic("java", "Core Java", "Core Java Description"),
			new topic("javascript", "Javascript", "Javascript Description")
			));
	
	public List<topic> getAllTopics()
	{
		return topics;
	}
	
	public topic getTopic(String id)
	{
		//lambda function to return the first topic instance that matches the id passed into the method
		return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
	}

	//this method is used for the POST request mapping to add to the 'topics' ArrayList
	public void addTopic(topic topic) {
		topics.add(topic);
	}

	//this method is used for the PUT request mapping to update a certain topic based on id
	public void updateTopic(topic topic, String id) {
		for(int i = 0; i < topics.size(); i++)
		{
			topic t = topics.get(i);
			if(t.getId().equals(id))
			{
				topics.set(i, topic);
				return;
			}
		}
	}

	public void deleteTopic(String id) {
		topics.removeIf(t -> t.getId().equals(id));
	}
}
