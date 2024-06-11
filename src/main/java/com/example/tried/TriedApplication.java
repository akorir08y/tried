package com.example.tried;

import com.example.tried.config.MpesaConfiguration;
import com.example.tried.dto.c2b.AcknowledgeResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableRetry
@EnableConfigurationProperties({MpesaConfiguration.class})
public class TriedApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TriedApplication.class, args);
	}
	@Bean
	public OkHttpClient getOkHttpClient() {
		OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.connectTimeout(90, TimeUnit.SECONDS)
				.readTimeout(90, TimeUnit.SECONDS)
				.writeTimeout(90, TimeUnit.SECONDS)
				.retryOnConnectionFailure(true)
				.followRedirects(false)
				.build();
		return okHttpClient;
	}

	// Global Instance of Object Mapper
	@Bean
	public ObjectMapper getObjectMapper() {
		ObjectMapper defaultObjectMapper = new ObjectMapper();
		defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		return defaultObjectMapper;
	}

	// Retry Template
	@Bean
	public RetryTemplate retryTemplate()
	{
		RetryTemplate retryTemplate = new RetryTemplate();

		FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
		backOffPolicy.setBackOffPeriod(100);

		SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
		simpleRetryPolicy.setMaxAttempts(2);

		retryTemplate.setRetryPolicy(simpleRetryPolicy);
		retryTemplate.setBackOffPolicy(backOffPolicy);
		return retryTemplate;
	}

	// Global Instance of Acknowledge Response
	@Bean
	public AcknowledgeResponse getAcknowledgeResponse(){
		AcknowledgeResponse acknowledgeResponse = new AcknowledgeResponse();
		acknowledgeResponse.setMessage("Success");
		return acknowledgeResponse;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(TriedApplication.class);
	}
}
