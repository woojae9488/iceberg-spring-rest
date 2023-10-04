package com.kwj.iceberg.springrest.business.auth.model;

import org.apache.iceberg.relocated.com.google.common.base.Preconditions;
import org.apache.iceberg.rest.RESTRequest;

public record OAuthTokenRequest(
	String grantType,
	String clientId,
	String clientSecret,
	String subjectToken,
	String subjectTokenType
) implements RESTRequest {

	@Override
	public void validate() {
		Preconditions.checkArgument(grantType != null, "Invalid grant type: null");

		switch (resolveGrantType()) {
			case CLIENT_CREDENTIALS -> {
				Preconditions.checkArgument(clientId != null, "Invalid client id: null");
				Preconditions.checkArgument(clientSecret != null, "Invalid client secret: null");
			}
			case TOKEN_EXCHANGE -> {
				Preconditions.checkArgument(subjectToken != null, "Invalid subject token: null");
				Preconditions.checkArgument(subjectTokenType != null, "Invalid subject token type: null");
			}
		}
	}

	public OAuthGrantType resolveGrantType() {
		return OAuthGrantType.resolve(grantType);
	}

}
