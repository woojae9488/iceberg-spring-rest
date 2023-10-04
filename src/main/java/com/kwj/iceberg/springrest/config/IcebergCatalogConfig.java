package com.kwj.iceberg.springrest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kwj.iceberg.springrest.config.factory.IcebergCatalogBeanRegistrar;
import com.kwj.iceberg.springrest.config.property.IcebergCatalogsProperties;

@Configuration
public class IcebergCatalogConfig {

	@Bean
	public IcebergCatalogBeanRegistrar icebergCatalogBeanRegistrar() {
		return new IcebergCatalogBeanRegistrar();
	}

	@Bean
	@ConfigurationProperties("iceberg")
	public IcebergCatalogsProperties icebergCatalogsProperties() {
		return new IcebergCatalogsProperties();
	}

}
