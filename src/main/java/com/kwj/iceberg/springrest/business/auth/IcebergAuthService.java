package com.kwj.iceberg.springrest.business.auth;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.iceberg.catalog.Catalog;
import org.apache.iceberg.rest.responses.OAuthTokenResponse;
import org.springframework.stereotype.Service;

import com.kwj.iceberg.springrest.business.auth.model.OAuthTokenRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IcebergAuthService implements IcebergAuthHandler {

	@Override
	public OAuthTokenResponse exchangeToken(Catalog catalog, OAuthTokenRequest request) {
		// TODO implements
		throw new NotImplementedException();
	}

}
