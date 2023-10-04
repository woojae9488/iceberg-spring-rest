package com.kwj.iceberg.springrest.business.auth;

import org.apache.iceberg.catalog.Catalog;
import org.apache.iceberg.rest.responses.OAuthTokenResponse;

import com.kwj.iceberg.springrest.business.auth.model.OAuthTokenRequest;

public interface IcebergAuthHandler {

	OAuthTokenResponse exchangeToken(Catalog catalog, OAuthTokenRequest request);

}
