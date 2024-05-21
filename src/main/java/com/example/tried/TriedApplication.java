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

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@ConfigurationPropertiesScan
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
