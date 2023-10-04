package com.kwj.iceberg.springrest.business.catalog.model;

import java.util.Map;

import org.apache.iceberg.CatalogProperties;
import org.apache.iceberg.catalog.Catalog;
import org.apache.iceberg.catalog.SupportsNamespaces;
import org.apache.iceberg.util.PropertyUtil;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class IcebergCatalogContext<T extends Catalog & SupportsNamespaces> {

	private final T catalog;
	private final boolean implicit;
	private final Map<String, String> properties;

	public Map<String, String> getDefaultProperties() {
		return PropertyUtil.propertiesWithPrefix(properties, CatalogProperties.TABLE_DEFAULT_PREFIX);
	}

	public Map<String, String> getOverrideProperties() {
		return PropertyUtil.propertiesWithPrefix(properties, CatalogProperties.TABLE_OVERRIDE_PREFIX);
	}

}
