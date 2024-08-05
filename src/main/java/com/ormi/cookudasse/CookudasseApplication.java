//package com.ormi.cookudasse;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class CookudasseApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(CookudasseApplication.class, args);
//	}
//
//
//}


package com.ormi.cookudasse;

import java.util.Arrays;
import java.util.TimeZone;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class CookudasseApplication implements ApplicationListener<ApplicationReadyEvent> {
	private final Environment environment;

	public static void main(String[] args) {
		init();
		SpringApplication.run(CookudasseApplication.class, args);
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		log.info("applicationReady status" + Arrays.toString(environment.getActiveProfiles()));
	}

	private static void init() {
		log.info("Spring Server TimeZone : Asia/Seoul");
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}
}
