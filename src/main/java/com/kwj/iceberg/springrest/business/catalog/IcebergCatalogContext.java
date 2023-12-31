package com.kwj.iceberg.springrest.business.catalog;

import java.util.Map;
import java.util.Optional;

import org.apache.iceberg.CatalogProperties;
import org.apache.iceberg.catalog.Catalog;
import org.apache.iceberg.catalog.SupportsNamespaces;
import org.apache.iceberg.util.PropertyUtil;

import com.kwj.iceberg.springrest.exception.IcebergSpringRestException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class IcebergCatalogContext<T extends Catalog> {

	private final T catalog;
	private final boolean implicit;
	private final Map<String, String> properties;

	public SupportsNamespaces getNamespaceCatalog() {
		return Optional.of(catalog)
			.filter(SupportsNamespaces.class::isInstance)
			.map(SupportsNamespaces.class::cast)
			.orElseThrow(() ->
				new IcebergSpringRestException("Catalog " + catalog.name() + " does not support namespaces"));
	}

	public Map<String, String> getDefaultProperties() {
		return PropertyUtil.propertiesWithPrefix(properties, CatalogProperties.TABLE_DEFAULT_PREFIX);
	}

	public Map<String, String> getOverrideProperties() {
		return PropertyUtil.propertiesWithPrefix(properties, CatalogProperties.TABLE_OVERRIDE_PREFIX);
	}

}
