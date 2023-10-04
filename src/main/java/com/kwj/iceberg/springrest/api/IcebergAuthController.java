package com.kwj.iceberg.springrest.api;

import org.apache.iceberg.rest.responses.OAuthTokenResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kwj.iceberg.springrest.business.auth.IcebergAuthHandler;
import com.kwj.iceberg.springrest.business.auth.model.OAuthTokenRequest;
import com.kwj.iceberg.springrest.business.catalog.IcebergCatalogSpec;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping({
	IcebergUriPaths.V1_PREFIX,
	IcebergUriPaths.CATALOG_V1_PREFIX
})
@RequiredArgsConstructor
public class IcebergAuthController {

	private final IcebergCatalogSpec catalogSpec;
	private final IcebergAuthHandler authHandler;

	@PostMapping(IcebergUriPaths.OAUTH_TOKENS)
	public OAuthTokenResponse exchangeToken(
		@PathVariable(required = false) String catalog,
		@RequestBody OAuthTokenRequest request
	) {
		return authHandler.exchangeToken(catalogSpec.catalog(catalog), request);
	}

}
