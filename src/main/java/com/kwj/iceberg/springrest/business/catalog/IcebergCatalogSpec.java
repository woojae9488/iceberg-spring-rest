package com.kwj.iceberg.springrest.business.catalog;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.iceberg.catalog.Catalog;
import org.springframework.stereotype.Service;

import com.kwj.iceberg.springrest.business.catalog.model.IcebergCatalogContext;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IcebergCatalogSpec {

	private final List<IcebergCatalogContext<?>> catalogContexts;

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
			.orElseThrow(() -> /* TODO */ new RuntimeException("Not Implemented"));
	}

}
