package com.kwj.iceberg.springrest.business.catalog;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.iceberg.catalog.Catalog;
import org.springframework.stereotype.Service;

import com.kwj.iceberg.springrest.config.property.IcebergCatalogProperties;
import com.kwj.iceberg.springrest.config.property.IcebergCatalogsProperties;
import com.kwj.iceberg.springrest.exception.IcebergSpringRestException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IcebergCatalogSpec {

	private final List<IcebergCatalogContext<?>> catalogContexts;

	public IcebergCatalogSpec(IcebergCatalogsProperties properties) {
		this.catalogContexts = createCatalogContexts(properties);
	}

	private static List<IcebergCatalogContext<?>> createCatalogContexts(IcebergCatalogsProperties properties) {
		return properties.getCatalogs()
			.entrySet()
			.stream()
			.map(catalog -> createCatalogContext(catalog.getKey(), catalog.getValue()))
			.collect(Collectors.toList());
	}

	private static IcebergCatalogContext<?> createCatalogContext(String name, IcebergCatalogProperties<?> properties) {
		Class<? extends Catalog> catalogClass = properties.getCatalogClass();

		try {
			Catalog catalog = catalogClass.getConstructor().newInstance();
			catalog.initialize(name, properties.getProperties());
			return IcebergCatalogContext.of(catalog, properties.isImplicit(), properties.getProperties());
		} catch (Exception e) {
			log.error("Failed to create catalog (name:{}, properties:{})", name, properties);
			throw new IcebergSpringRestException(e);
		}
	}

	public Catalog catalog(String catalogName) {
		return getCatalogContext(catalogName).getCatalog();
	}

	public IcebergCatalogHandler catalogHandler(String catalogName) {
		return IcebergCatalogAdapter.of(getCatalogContext(catalogName));
	}

	private IcebergCatalogContext<?> getCatalogContext(String catalogName) {
		return catalogContexts.stream()
			.filter(context -> StringUtils.equals(catalogName, context.getCatalog().name()))
			.findFirst()
			.orElseGet(this::getImplicitCatalogContext);
	}

	private IcebergCatalogContext<?> getImplicitCatalogContext() {
		return catalogContexts.stream()
			.filter(IcebergCatalogContext::isImplicit)
			.findFirst()
			.orElseThrow(() -> new IcebergSpringRestException("Not found implicit catalog"));
	}

}
