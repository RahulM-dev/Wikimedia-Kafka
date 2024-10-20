package com.wikimedia.kafka;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import com.wikimedia.event.WikimediaMessageHandler;

@Service
public class WikiMessageProducer {
	
	 @Value("${spring.kafka.topic.name}")
	 private String topicName;

	private static final Logger LOGGER = LoggerFactory.getLogger(WikiMessageProducer.class);
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void sendMessage() throws InterruptedException {
		
		//To read real time continuous data from wikimedia, we will use event source
		
		EventHandler eventHandler = new WikimediaMessageHandler(kafkaTemplate, topicName);
		String url = "https://stream.wikimedia.org/v2/stream/recentchange";
		
		EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
		EventSource eventSource = builder.build();
		eventSource.start();
		
		TimeUnit.MINUTES.sleep(10);
	}
}
