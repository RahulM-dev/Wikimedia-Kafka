package com.wikimedia.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WikimediaMessageHandler implements EventHandler{
	
	//Whenever there will be a new message in the wikimedia this class will be triggered
	
	//And as we have overriden the onMessage method so the message will be in the messageEvent object and it will be
	//sent to the kafka broker
	
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("{spring.kafka.topic.name}")
	private String topicName;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaMessageHandler.class);

	@Override
	public void onOpen() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClosed() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(String event, MessageEvent messageEvent) throws Exception {

		LOGGER.info(String.format("Event Data -> %s", messageEvent.getData()));
		kafkaTemplate.send(topicName, messageEvent.getData());
	}

	@Override
	public void onComment(String comment) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(Throwable t) {
		// TODO Auto-generated method stub
		
	}

}
