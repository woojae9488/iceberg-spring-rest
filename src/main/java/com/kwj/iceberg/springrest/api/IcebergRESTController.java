package com.kwj.iceberg.springrest.api;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping({
	IcebergRESTPaths.V1_PREFIX,
	IcebergRESTPaths.CATALOG_V1_PREFIX,
	IcebergRESTPaths.V1_CATALOG_PREFIX
})
@RequiredArgsConstructor
public class IcebergRESTController {

	@GetMapping(IcebergRESTPaths.CONFIG)
	public ConfigResponse getConfig(
		@PathVariable(required = false) String catalog
	) {
		return null;
	}

	@GetMapping(IcebergRESTPaths.NAMESPACES)
	public ListNamespacesResponse listNamespaces(
		@PathVariable(required = false) String catalog
	) {
		return null;
	}

	@PostMapping(IcebergRESTPaths.NAMESPACES)
	public CreateNamespaceResponse createNamespace(
		@PathVariable(required = false) String catalog,
		@RequestBody CreateNamespaceRequest request
	) {
		return null;
	}

	@GetMapping(IcebergRESTPaths.NAMESPACE)
	public GetNamespaceResponse loadNamespace(
		@PathVariable(required = false) String catalog,
		@PathVariable String namespace
	) {
		return null;
	}

	@DeleteMapping(IcebergRESTPaths.NAMESPACE)
	public void dropNamespace(
		@PathVariable(required = false) String catalog,
		@PathVariable String namespace
	) {
	}

	@PostMapping(IcebergRESTPaths.NAMESPACE_PROPERTIES)
	public UpdateNamespacePropertiesResponse updateNamespaceProperties(
		@PathVariable(required = false) String catalog,
		@PathVariable String namespace,
		@RequestBody UpdateNamespacePropertiesRequest request
	) {
		return null;
	}

	@GetMapping(IcebergRESTPaths.NAMESPACE_TABLES)
	public ListTablesResponse listTables(
		@PathVariable(required = false) String catalog,
		@PathVariable String namespace
	) {
		return null;
	}

	@PostMapping(IcebergRESTPaths.NAMESPACE_TABLES)
	public LoadTableResponse createTable(
		@PathVariable(required = false) String catalog,
		@PathVariable String namespace,
		@RequestBody CreateTableRequest request
	) {
		return null;
	}

	@GetMapping(IcebergRESTPaths.NAMESPACE_TABLE)
	public LoadTableResponse loadTable(
		@PathVariable(required = false) String catalog,
		@PathVariable String namespace,
		@PathVariable String table
	) {
		return null;
	}

	@PostMapping(IcebergRESTPaths.NAMESPACE_TABLE)
	public LoadTableResponse updateTable(
		@PathVariable(required = false) String catalog,
		@PathVariable String namespace,
		@PathVariable String table,
		@RequestBody UpdateTableRequest request
	) {
		return null;
	}

	@DeleteMapping(IcebergRESTPaths.NAMESPACE_TABLE)
	public void dropTable(
		@PathVariable(required = false) String catalog,
		@PathVariable String namespace,
		@PathVariable String table
	) {
	}

	@PostMapping(IcebergRESTPaths.TABLES_RENAME)
	public void renameTable(
		@PathVariable(required = false) String catalog,
		@RequestBody RenameTableRequest request
	) {
	}

	@PostMapping(IcebergRESTPaths.NAMESPACE_TABLE_METRICS)
	public void reportMetrics(
		@PathVariable(required = false) String catalog,
		@PathVariable String namespace,
		@PathVariable String table,
		@RequestBody ReportMetricsRequest request
	) {
	}

}
