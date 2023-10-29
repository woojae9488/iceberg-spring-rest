package com.kwj.iceberg.springrest.business.catalog;

import org.apache.iceberg.catalog.Namespace;
import org.apache.iceberg.catalog.TableIdentifier;
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

public interface IcebergCatalogHandler {

	ConfigResponse getConfig();

	ListNamespacesResponse listNamespaces(Namespace parent);

	CreateNamespaceResponse createNamespace(CreateNamespaceRequest request);

	GetNamespaceResponse loadNamespace(Namespace namespace);

	void dropNamespace(Namespace namespace);

	UpdateNamespacePropertiesResponse updateNamespaceProperties(
		Namespace namespace, UpdateNamespacePropertiesRequest request);

	ListTablesResponse listTables(Namespace namespace);

	LoadTableResponse createTable(Namespace namespace, CreateTableRequest request);

	LoadTableResponse loadTable(TableIdentifier table);

	LoadTableResponse updateTable(TableIdentifier table, UpdateTableRequest request);

	void dropTable(TableIdentifier table, boolean purgeRequested);

	void checkTable(TableIdentifier table);

	void renameTable(RenameTableRequest request);

	void reportMetrics(TableIdentifier table, ReportMetricsRequest request);

}
