package com.kwj.iceberg.springrest.config.property;

import java.util.Map;

import org.springframework.lang.NonNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IcebergCatalogsProperties {

	@NonNull
	private Map<String, IcebergCatalogProperties<?>> catalogs;

}
