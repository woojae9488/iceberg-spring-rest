package com.kwj.iceberg.springrest.api;

public class IcebergUriPaths {

	public static final String CATALOG_V1_PREFIX = "/{catalog}/v1";

	public static final String V1_CATALOG_PREFIX = "/v1/{catalog}";

	public static final String V1_PREFIX = "/v1";

	public static final String CONFIG = "/config";

	public static final String OAUTH_TOKENS = "/oauth/tokens";

	public static final String NAMESPACES = "/namespaces";

	public static final String NAMESPACE = "/namespaces/{namespace}";

	public static final String NAMESPACE_PROPERTIES = "/namespaces/{namespace}/properties";

	public static final String NAMESPACE_TABLES = "/namespaces/{namespace}/tables";

	public static final String NAMESPACE_TABLE = "/namespaces/{namespace}/tables/{table}";

	public static final String TABLES_RENAME = "/tables/rename";

	public static final String NAMESPACE_TABLE_METRICS = "/namespaces/{namespace}/tables/{table}/metrics";

}
