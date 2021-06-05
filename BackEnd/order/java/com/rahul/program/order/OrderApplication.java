package com.rahul.program.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2

public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}
	  @Bean
	  @LoadBalanced
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }

	    @Bean
	    public HttpHeaders headers() {
	        return new HttpHeaders();
	    }
	    
	    
	    @Bean
	    public LocalValidatorFactoryBean localValidatorFactoryBean() {
	        return new LocalValidatorFactoryBean();
	    }

	    @Bean
	    public ValidatingMongoEventListener validatingMongoEventListener(LocalValidatorFactoryBean lfb) {
	        return new ValidatingMongoEventListener(lfb);
	    }

}
