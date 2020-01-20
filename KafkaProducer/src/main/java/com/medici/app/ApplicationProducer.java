package com.medici.app;

import java.util.Calendar;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.medici.app.kafka.model.StackHolder;
import com.medici.app.runner.LookupRunnerService;

@SpringBootApplication
@EnableAsync
public class ApplicationProducer implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationProducer.class);

	@Autowired
	LookupRunnerService service;

	public static void main(String[] args) {
		SpringApplication.run(ApplicationProducer.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		long start = System.currentTimeMillis();

		int i = 0;
		while (i < 10) {

			int j = 1;

			CompletableFuture<StackHolder> job1 = service.send(new StackHolder(String.valueOf(Calendar.getInstance().getTimeInMillis() + j++), 10, "level", "experience", "domain"));
			CompletableFuture<StackHolder> job2 = service.send(new StackHolder(String.valueOf(Calendar.getInstance().getTimeInMillis() + j++), 20, "level", "experience", "domain"));
			CompletableFuture<StackHolder> job3 = service.send(new StackHolder(String.valueOf(Calendar.getInstance().getTimeInMillis() + j++), 30, "level", "experience", "domain"));
			CompletableFuture<StackHolder> job4 = service.send(new StackHolder(String.valueOf(Calendar.getInstance().getTimeInMillis() + j++), 40, "level", "experience", "domain"));
			CompletableFuture<StackHolder> job5 = service.send(new StackHolder(String.valueOf(Calendar.getInstance().getTimeInMillis() + j++), 50, "level", "experience", "domain"));
			CompletableFuture<StackHolder> job6 = service.send(new StackHolder(String.valueOf(Calendar.getInstance().getTimeInMillis() + j++), 60, "level", "experience", "domain"));
			CompletableFuture<StackHolder> job7 = service.send(new StackHolder(String.valueOf(Calendar.getInstance().getTimeInMillis() + j++), 70, "level", "experience", "domain"));
			CompletableFuture<StackHolder> job8 = service.send(new StackHolder(String.valueOf(Calendar.getInstance().getTimeInMillis() + j++), 80, "level", "experience", "domain"));
			CompletableFuture<StackHolder> job9 = service.send(new StackHolder(String.valueOf(Calendar.getInstance().getTimeInMillis() + j++), 90, "level", "experience", "domain"));
			CompletableFuture<StackHolder> job10 = service.send(new StackHolder(String.valueOf(Calendar.getInstance().getTimeInMillis() + j++), 100, "level", "experience", "domain"));

			CompletableFuture.allOf(job1, job2, job3, job4, job5, job6, job7, job8, job9, job10).join();

			logger.info("||>>>>> " + job1.get());
			logger.info("||>>>>> " + job2.get());
			logger.info("||>>>>> " + job3.get());
			logger.info("||>>>>> " + job4.get());
			logger.info("||>>>>> " + job5.get());
			logger.info("||>>>>> " + job6.get());
			logger.info("||>>>>> " + job7.get());
			logger.info("||>>>>> " + job8.get());
			logger.info("||>>>>> " + job9.get());
			logger.info("||>>>>> " + job10.get());

			i++;
		}

		logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
	}

	@Bean("threadPoolTaskExecutor")
	public TaskExecutor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(5);
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setThreadNamePrefix("Async-");
		return executor;
	}

}