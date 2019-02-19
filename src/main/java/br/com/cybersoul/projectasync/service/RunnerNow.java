package br.com.cybersoul.projectasync.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.cybersoul.projectasync.model.SkyObject;

@Component
public class RunnerNow implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(RunnerNow.class);
	private final EyesOnTheSkyService eyesOnTheSkyService;
	
	public RunnerNow(EyesOnTheSkyService eyesOnTheSkyService) {
		this.eyesOnTheSkyService = eyesOnTheSkyService;
	}
	
	@Override
	public void run(String[] args) throws Exception {
		long start = System.currentTimeMillis();

		CompletableFuture<SkyObject> page1 = eyesOnTheSkyService.findSky("2019-02-01");
		CompletableFuture<SkyObject> page2 = eyesOnTheSkyService.findSky("2019-02-05");
		CompletableFuture<SkyObject> page3 = eyesOnTheSkyService.findSky("2019-02-18");

		CompletableFuture.allOf(page1, page2, page3, page3).join();

		System.out.println("===================================================");
		System.out.println("Copyright......: " + page1.get().getCopyright());
		System.out.println("Date...........: " + page1.get().getDate());
		System.out.println("Explanation....: " + page1.get().getExplanation());
		System.out.println("HdURL..........: " + page1.get().getHdurl());
		System.out.println("Media type.....: " + page1.get().getMedia_type());
		System.out.println("Service version: " + page1.get().getService_version());
		System.out.println("Title..........: " + page1.get().getTitle());
		System.out.println("Url............: " + page1.get().getUrl());
		System.out.println("===================================================");
		System.out.println("Copyright......: " + page2.get().getCopyright());
		System.out.println("Date...........: " + page2.get().getDate());
		System.out.println("Explanation....: " + page2.get().getExplanation());
		System.out.println("HdURL..........: " + page2.get().getHdurl());
		System.out.println("Media type.....: " + page2.get().getMedia_type());
		System.out.println("Service version: " + page2.get().getService_version());
		System.out.println("Title..........: " + page2.get().getTitle());
		System.out.println("Url............: " + page2.get().getUrl());
		System.out.println("===================================================");		
		System.out.println("Copyright......: " + page3.get().getCopyright());
		System.out.println("Date...........: " + page3.get().getDate());
		System.out.println("Explanation....: " + page3.get().getExplanation());
		System.out.println("HdURL..........: " + page3.get().getHdurl());
		System.out.println("Media type.....: " + page3.get().getMedia_type());
		System.out.println("Service version: " + page3.get().getService_version());
		System.out.println("Title..........: " + page3.get().getTitle());
		System.out.println("Url............: " + page3.get().getUrl());
		System.out.println("===================================================");

		logger.info("Elapse time: " + (System.currentTimeMillis() - start));
		logger.info("--> " + page1);
		logger.info("--> " + page2);
		logger.info("--> " + page3);
	}
}
