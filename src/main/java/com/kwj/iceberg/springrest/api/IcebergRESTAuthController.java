package com.kwj.iceberg.springrest.api;

import org.apache.iceberg.rest.responses.OAuthTokenResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping({
	IcebergRESTPaths.V1_PREFIX,
	IcebergRESTPaths.CATALOG_V1_PREFIX
})
@RequiredArgsConstructor
public class IcebergRESTAuthController {

	@PostMapping(IcebergRESTPaths.OAUTH_TOKENS)
	public OAuthTokenResponse exchangeToken(
		@PathVariable(required = false) String catalog
	) {
		return null;
	}

}
