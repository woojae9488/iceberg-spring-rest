package com.kwj.iceberg.springrest.business.auth.model;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum OAuthGrantType {

	CLIENT_CREDENTIALS("client_credentials"),
	TOKEN_EXCHANGE("urn:ietf:params:oauth:grant-type:token-exchange");

	@Getter(AccessLevel.PRIVATE)
	private final String fullName;

	private static final Map<String, OAuthGrantType> CACHE = Arrays.stream(values())
		.collect(Collectors.toMap(OAuthGrantType::getFullName, Function.identity()));

	public static OAuthGrantType resolve(String grantType) {
		return Optional.ofNullable(grantType)
			.map(CACHE::get)
			.orElseThrow(() -> /* TODO */ new RuntimeException("Not Implemented"));
	}

}
