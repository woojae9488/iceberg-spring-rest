package com.kwj.iceberg.springrest.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.iceberg.catalog.Namespace;
import org.apache.iceberg.catalog.TableIdentifier;
import org.apache.iceberg.rest.RESTUtil;

public class IcebergNameUtils {

	public static Namespace resolveNamespace(String namespace) {
		return StringUtils.isNotEmpty(namespace)
			? Namespace.of(RESTUtil.NAMESPACE_SPLITTER.splitToStream(namespace).toArray(String[]::new))
			: Namespace.empty();
	}

	public static TableIdentifier resolveTable(String namespace, String table) {
		return TableIdentifier.of(resolveNamespace(namespace), table);
	}

}
