package com.kwj.iceberg.springrest.business.catalog;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.iceberg.catalog.Namespace;
import org.apache.iceberg.catalog.TableIdentifier;
import org.apache.iceberg.rest.CatalogHandlers;
import org.apache.iceberg.rest.requests.CreateNamespaceRequest;
import org.apache.iceberg.rest.requests.CreateTableRequest;
import org.apache.iceberg.rest.requests.RenameTableRequest;
import org.apache.iceberg.rest.requests.ReportMetricsRequest;
import org.apache.iceberg.rest.requests.UpdateNamespacePropertiesRequest;
import org.apache.iceberg.rest.requests.UpdateTableRequest;
import org.apache.iceberg.rest.responses.ConfigResponse;
import org.apache.iceberg.rest.responses.CreateNamespaceResponse;
import org.apache.iceberg.rest.responses.GetNamespaceResponse;
import org.apache.iceberg.rest.responses.ListNamespacesResponse;
import org.apache.iceberg.rest.responses.ListTablesResponse;
import org.apache.iceberg.rest.responses.LoadTableResponse;
import org.apache.iceberg.rest.responses.UpdateNamespacePropertiesResponse;

import com.kwj.iceberg.springrest.business.catalog.model.IcebergCatalogContext;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
public class IcebergCatalogAdapter implements IcebergCatalogHandler {

	private final IcebergCatalogContext<?> catalogContext;

	@Override
	public ConfigResponse getConfig() {
		return ConfigResponse.builder()
			.withDefaults(catalogContext.getDefaultProperties())
			.withOverrides(catalogContext.getOverrideProperties())
			.build();
	}

	@Override
	public ListNamespacesResponse listNamespaces(Namespace parent) {
		return CatalogHandlers.listNamespaces(catalogContext.getCatalog(), parent);
	}

	@Override
	public CreateNamespaceResponse createNamespace(CreateNamespaceRequest request) {
		return CatalogHandlers.createNamespace(catalogContext.getCatalog(), request);
	}

	@Override
	public GetNamespaceResponse loadNamespace(Namespace namespace) {
		return CatalogHandlers.loadNamespace(catalogContext.getCatalog(), namespace);
	}

	@Override
	public void dropNamespace(Namespace namespace) {
		CatalogHandlers.dropNamespace(catalogContext.getCatalog(), namespace);
	}

	@Override
	public UpdateNamespacePropertiesResponse updateNamespaceProperties(
		Namespace namespace, UpdateNamespacePropertiesRequest request) {
		return CatalogHandlers.updateNamespaceProperties(catalogContext.getCatalog(), namespace, request);
	}

	@Override
	public ListTablesResponse listTables(Namespace namespace) {
		return CatalogHandlers.listTables(catalogContext.getCatalog(), namespace);
	}

	@Override
	public LoadTableResponse createTable(Namespace namespace, CreateTableRequest request) {
		return request.stageCreate()
			? CatalogHandlers.stageTableCreate(catalogContext.getCatalog(), namespace, request)
			: CatalogHandlers.createTable(catalogContext.getCatalog(), namespace, request);
	}

	@Override
	public LoadTableResponse loadTable(TableIdentifier table) {
		return CatalogHandlers.loadTable(catalogContext.getCatalog(), table);
	}

	@Override
	public LoadTableResponse updateTable(TableIdentifier table, UpdateTableRequest request) {
		return CatalogHandlers.updateTable(catalogContext.getCatalog(), table, request);
	}

	@Override
	public void dropTable(TableIdentifier table, boolean purgeRequested) {
		if (purgeRequested) {
			CatalogHandlers.purgeTable(catalogContext.getCatalog(), table);
		} else {
			CatalogHandlers.dropTable(catalogContext.getCatalog(), table);
		}
	}

	@Override
	public void checkTable(TableIdentifier table) {
		catalogContext.getCatalog().loadTable(table);
	}

	@Override
	public void renameTable(RenameTableRequest request) {
		CatalogHandlers.renameTable(catalogContext.getCatalog(), request);
	}

	@Override
	public void reportMetrics(TableIdentifier table, ReportMetricsRequest request) {
		// TODO implements
		throw new NotImplementedException();
	}

}
