package com.kwj.iceberg.springrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.kwj.iceberg.springrest")
public class IcebergSpringRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(IcebergSpringRestApplication.class, args);
	}

}
