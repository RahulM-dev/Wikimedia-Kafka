package com.wikimedia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wikimedia.kafka.WikiMessageProducer;

@SpringBootApplication
public class WikimediaKafkaProducerApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(WikimediaKafkaProducerApplication.class, args);
	}
	
	@Autowired
	private WikiMessageProducer wikiMessageProducer;

	@Override
	public void run(String... args) throws Exception {
		wikiMessageProducer.sendMessage();
	}
	
	

}
