package com.kwj.iceberg.springrest.config.property;

import java.util.Map;

import org.apache.iceberg.catalog.Catalog;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IcebergCatalogProperties<T extends Catalog> {

	@NonNull
	private Class<T> catalogClass;

	private boolean implicit;

	private Map<String, String> properties;

}
