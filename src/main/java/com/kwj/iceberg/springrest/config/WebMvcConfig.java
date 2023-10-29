package com.kwj.iceberg.springrest.config;

import java.util.List;

import org.apache.iceberg.rest.RESTSerializers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

	@Bean
	public HttpMessageConverter<?> icebergMessageConverter() {
		ObjectMapper objectMapper = new ObjectMapper(new JsonFactory())
			.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
			.setPropertyNamingStrategy(PropertyNamingStrategies.KEBAB_CASE)
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		RESTSerializers.registerAll(objectMapper);
		return new MappingJackson2HttpMessageConverter(objectMapper);
	}

	@Override
	protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(icebergMessageConverter());
		super.addDefaultHttpMessageConverters(converters);
	}

}
