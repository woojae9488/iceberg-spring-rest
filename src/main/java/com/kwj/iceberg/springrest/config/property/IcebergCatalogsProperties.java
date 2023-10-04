package com.kwj.iceberg.springrest.config.property;

import java.util.Map;

import org.apache.iceberg.catalog.Catalog;
import org.apache.iceberg.catalog.SupportsNamespaces;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IcebergCatalogsProperties {

	private Map<String, IcebergCatalogProperties<?>> catalogs;

	@Getter
	@Setter
	@ToString
	public static class IcebergCatalogProperties<T extends Catalog & SupportsNamespaces> {

		private Class<T> catalogClass;

		private boolean implicit;

		private Map<String, String> properties;

	}

}
