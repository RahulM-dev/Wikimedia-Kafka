package com.wikimedia.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.wikimedia.entity.WikimediaData;
import com.wikimedia.repository.WikimediaDataRepository;

@Service
public class WikimediaMessageConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaMessageConsumer.class);
	
	@Autowired
	private WikimediaDataRepository wikimediaDataRepository;
	
	@KafkaListener(topics = "wikimedia-topic", groupId = "myConsumerGroup")
	public void consume(String eventMessage) {
		LOGGER.info(String.format("Event Message Received -> %s", eventMessage));
		
		WikimediaData wikimediaData = new WikimediaData();
		wikimediaData.setWikimediaEventData(eventMessage);
		
		wikimediaDataRepository.save(wikimediaData);
	}
}
