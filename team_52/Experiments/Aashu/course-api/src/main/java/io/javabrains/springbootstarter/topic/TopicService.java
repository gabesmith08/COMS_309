package io.javabrains.springbootstarter.topic;
//going to be a business service
//other class depends on this service

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TopicService {
	private List<Topic> topics = new ArrayList<>(Arrays.asList(
			
			new Topic("Spring", "spring framework","Spring description" ),
			new Topic("Spring", "spring framework","Spring description" ),
			new Topic("Spring", "spring framework","Spring description" )
			
			
			));
	
	public List<Topic> getAllTopics() {
		
		return topics;
	}
	public Topic getTopic(String id) {
		return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();//lambda expression
		
		/*for(Topic topic : topics){
		      if(topic.getId().equals(id)){
		         return topic;
		      }
		   }
		   return null;
		}﻿*/
		//above is the normal code for the lambda
	}
	
	public void addTopic(Topic topic) {
		// TODO Auto-generated method stub
		topics.add(topic);
	}
	public void updateTopic(Topic id2, Object id) {
		// TODO Auto-generated method stub
		for (int i =0; i<topics.size();i++) {
			Topic t =topics.get(i);
			if(t.getId().equals(id)) {
				topics.set(i,id2);
				return;
			}
		}
	}
	public void deleteTopic(Topic id2, Object id) {
		// TODO Auto-generated method stub
		for (int i =0; i<topics.size();i++) {
			Topic t =topics.get(i);
			if(t.getId().equals(id)) {
				topics.set(i,id2);
				return;
			}
		}
	}
	

}
