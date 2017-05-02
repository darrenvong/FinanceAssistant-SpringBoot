package com.cloudteam6.financeassistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
public class FinanceAssistantApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceAssistantApplication.class, args);
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		source.setBasename("classpath:validation");
		source.setUseCodeAsDefaultMessage(true);
		return source;
		
	}
}
