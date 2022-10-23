package com.bpop.kaisen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.Locale;

@SpringBootApplication
@EnableConfigurationProperties
public class AOPExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(AOPExampleApplication.class, args);
		Locale.setDefault(Locale.forLanguageTag("es_CO"));
	}

}
