package com.kwj.iceberg.springrest.servlet;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.UUID;

import org.apache.hadoop.conf.Configuration;
import org.apache.iceberg.CatalogProperties;
import org.apache.iceberg.catalog.Catalog;
import org.apache.iceberg.jdbc.JdbcCatalog;
import org.apache.iceberg.rest.RESTCatalogAdapter;
import org.apache.iceberg.rest.RESTCatalogServlet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.google.common.collect.ImmutableMap;

@org.springframework.context.annotation.Configuration
public class IcebergServletConfig {

	@Bean
	@ConditionalOnMissingBean(Catalog.class)
	public Catalog memoryCatalog() throws IOException {
		Map<String, String> catalogProperties = ImmutableMap.of(
			CatalogProperties.WAREHOUSE_LOCATION, Files.createTempDirectory("iceberg-").toString(),
			CatalogProperties.URI, "jdbc:sqlite:file::memory:?ic" + UUID.randomUUID().toString().replace("-", ""),
			JdbcCatalog.PROPERTY_PREFIX + "username", "user",
			JdbcCatalog.PROPERTY_PREFIX + "password", "password"
		);

		JdbcCatalog memoryCatalog = new JdbcCatalog();
		memoryCatalog.setConf(new Configuration());
		memoryCatalog.initialize("memory", catalogProperties);
		return memoryCatalog;
	}

	@Bean
	public ServletRegistrationBean<RESTCatalogServlet> restCatalogServlet(Catalog catalog) {
		return new ServletRegistrationBean<>(new RESTCatalogServlet(new RESTCatalogAdapter(catalog)), "/");
	}

}
