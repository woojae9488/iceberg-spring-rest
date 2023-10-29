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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kwj.iceberg.springrest.business.catalog.IcebergCatalogSpec;
import com.kwj.iceberg.springrest.util.IcebergNameUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping({
	IcebergUriPaths.V1_PREFIX,
	IcebergUriPaths.V1_CATALOG_PREFIX,
	IcebergUriPaths.CATALOG_V1_PREFIX
})
@RequiredArgsConstructor
public class IcebergCatalogController {

	private final IcebergCatalogSpec catalogSpec;

	@GetMapping(IcebergUriPaths.CONFIG)
	public ConfigResponse getConfig(
		@PathVariable(required = false) String catalog
	) {
		return catalogSpec.catalogHandler(catalog).getConfig();
	}

	@GetMapping(IcebergUriPaths.NAMESPACES)
	public ListNamespacesResponse listNamespaces(
		@PathVariable(required = false) String catalog,
		@RequestParam(required = false) String parent
	) {
		return catalogSpec.catalogHandler(catalog).listNamespaces(IcebergNameUtils.resolveNamespace(parent));
	}

	@PostMapping(IcebergUriPaths.NAMESPACES)
	public CreateNamespaceResponse createNamespace(
		@PathVariable(required = false) String catalog,
		@RequestBody CreateNamespaceRequest request
	) {
		return catalogSpec.catalogHandler(catalog).createNamespace(request);
	}

	@GetMapping(IcebergUriPaths.NAMESPACE)
	public GetNamespaceResponse loadNamespace(
		@PathVariable(required = false) String catalog,
		@PathVariable String namespace
	) {
		return catalogSpec.catalogHandler(catalog).loadNamespace(IcebergNameUtils.resolveNamespace(namespace));
	}

	@DeleteMapping(IcebergUriPaths.NAMESPACE)
	public void dropNamespace(
		@PathVariable(required = false) String catalog,
		@PathVariable String namespace
	) {
		catalogSpec.catalogHandler(catalog).dropNamespace(IcebergNameUtils.resolveNamespace(namespace));
	}

	@PostMapping(IcebergUriPaths.NAMESPACE_PROPERTIES)
	public UpdateNamespacePropertiesResponse updateNamespaceProperties(
		@PathVariable(required = false) String catalog,
		@PathVariable String namespace,
		@RequestBody UpdateNamespacePropertiesRequest request
	) {
		return catalogSpec.catalogHandler(catalog)
			.updateNamespaceProperties(IcebergNameUtils.resolveNamespace(namespace), request);
	}

	@GetMapping(IcebergUriPaths.NAMESPACE_TABLES)
	public ListTablesResponse listTables(
		@PathVariable(required = false) String catalog,
		@PathVariable String namespace
	) {
		return catalogSpec.catalogHandler(catalog).listTables(IcebergNameUtils.resolveNamespace(namespace));
	}

	@PostMapping(IcebergUriPaths.NAMESPACE_TABLES)
	public LoadTableResponse createTable(
		@PathVariable(required = false) String catalog,
		@PathVariable String namespace,
		@RequestBody CreateTableRequest request
	) {
		return catalogSpec.catalogHandler(catalog).createTable(IcebergNameUtils.resolveNamespace(namespace), request);
	}

	@GetMapping(IcebergUriPaths.NAMESPACE_TABLE)
	public LoadTableResponse loadTable(
		@PathVariable(required = false) String catalog,
		@PathVariable String namespace,
		@PathVariable String table
	) {
		return catalogSpec.catalogHandler(catalog).loadTable(IcebergNameUtils.resolveTable(namespace, table));
	}

	@PostMapping(IcebergUriPaths.NAMESPACE_TABLE)
	public LoadTableResponse updateTable(
		@PathVariable(required = false) String catalog,
		@PathVariable String namespace,
		@PathVariable String table,
		@RequestBody UpdateTableRequest request
	) {
		return catalogSpec.catalogHandler(catalog)
			.updateTable(IcebergNameUtils.resolveTable(namespace, table), request);
	}

	@DeleteMapping(IcebergUriPaths.NAMESPACE_TABLE)
	public void dropTable(
		@PathVariable(required = false) String catalog,
		@PathVariable String namespace,
		@PathVariable String table,
		@RequestParam(required = false) boolean purgeRequested
	) {
		catalogSpec.catalogHandler(catalog).dropTable(IcebergNameUtils.resolveTable(namespace, table), purgeRequested);
	}

	@RequestMapping(
		method = RequestMethod.HEAD,
		path = IcebergUriPaths.NAMESPACE_TABLE
	)
	public void checkTable(
		@PathVariable(required = false) String catalog,
		@PathVariable String namespace,
		@PathVariable String table
	) {
		catalogSpec.catalogHandler(catalog).checkTable(IcebergNameUtils.resolveTable(namespace, table));
	}

	@PostMapping(IcebergUriPaths.TABLES_RENAME)
	public void renameTable(
		@PathVariable(required = false) String catalog,
		@RequestBody RenameTableRequest request
	) {
		catalogSpec.catalogHandler(catalog).renameTable(request);
	}

	@PostMapping(IcebergUriPaths.NAMESPACE_TABLE_METRICS)
	public void reportMetrics(
		@PathVariable(required = false) String catalog,
		@PathVariable String namespace,
		@PathVariable String table,
		@RequestBody ReportMetricsRequest request
	) {
		catalogSpec.catalogHandler(catalog).reportMetrics(IcebergNameUtils.resolveTable(namespace, table), request);
	}

}
