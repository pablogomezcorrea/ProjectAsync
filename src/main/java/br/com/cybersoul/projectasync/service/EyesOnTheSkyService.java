package br.com.cybersoul.projectasync.service;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.cybersoul.projectasync.model.SkyObject;

@Service
public class EyesOnTheSkyService {
	private static final Logger logger = LoggerFactory.getLogger(EyesOnTheSkyService.class);
	private final RestTemplate restTemplate;
	
	public EyesOnTheSkyService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	@Async
	public CompletableFuture<SkyObject> findSky(String dateEvent) throws InterruptedException {
		logger.info("Searching ..." + dateEvent);
		String key = "1jD22Q0tzZoyGv6UwvrtKmurMfFHncH1SeGtFw6T";
		String url = "https://api.nasa.gov/planetary/apod?date=" + dateEvent + "&api_key=" + key;
		SkyObject results = restTemplate.getForObject(url, SkyObject.class); 
		Thread.sleep(4000L);
		return CompletableFuture.completedFuture(results);
	}
}
