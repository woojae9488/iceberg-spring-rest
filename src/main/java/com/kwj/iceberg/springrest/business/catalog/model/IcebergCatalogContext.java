package com.kwj.iceberg.springrest.business.catalog.model;

import java.util.Map;

import org.apache.iceberg.CatalogProperties;
import org.apache.iceberg.catalog.Catalog;
import org.apache.iceberg.catalog.SupportsNamespaces;
import org.apache.iceberg.util.PropertyUtil;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class IcebergCatalogContext<T extends Catalog & SupportsNamespaces> {

	private final T catalog;
	private final boolean implicit;
	private final Map<String, String> properties;

	public static <T extends Catalog & SupportsNamespaces> IcebergCatalogContext<T> of(
		Class<T> clazz,
		String name,
		boolean implicit,
		Map<String, String> properties
	) throws ReflectiveOperationException {
		T catalog = clazz.getConstructor().newInstance();
		catalog.initialize(name, properties);
		return new IcebergCatalogContext<>(catalog, implicit, properties);
	}

	public Map<String, String> getDefaultProperties() {
		return PropertyUtil.propertiesWithPrefix(properties, CatalogProperties.TABLE_DEFAULT_PREFIX);
	}

	public Map<String, String> getOverrideProperties() {
		return PropertyUtil.propertiesWithPrefix(properties, CatalogProperties.TABLE_OVERRIDE_PREFIX);
	}

}
