package com.user.survey.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
			
	static final String CLIEN_ID = "64b04dd8-cf4f-4356-958b-765e3d8f4338";
	static final String CLIENT_SECRET = "$2a$10$/JJH0K48OkzYPljUJwWiuetevbN5l/8mO7G7dRFNrA5o9SUTRUSzC";
	static final String GRANT_TYPE_PASSWORD = "password";
	static final String AUTHORIZATION_CODE = "authorization_code";
	static final String REFRESH_TOKEN = "refresh_token";
	static final String IMPLICIT = "implicit";
	static final String SCOPE_READ = "read";
	static final String SCOPE_WRITE = "write";
	static final String TRUST = "trust";
	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 2*60*60;
	static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 48*60*60;

	
	private String privateKey = "-----BEGIN RSA PRIVATE KEY-----"
			+"MIIEogIBAAKCAQB2kwULXu7YGoLdMg0nVSesoSPUf0tvvyeNYz81H9gCYNG0eELX"
			+"OJP+luA+MJVIiWAqZ2dXpLSKAdeeX9c1nm4cluj1HUGi0etD9HDr44BjyUYPv56Y"
			+"vLkvk99OBVVDF1RwvZ0fwlYNekzWkQAse09zwmpzhBTmhblVtwAKCQ9J0Qr50WCU"
			+"IXpptWM9o2gY3vgMTP0pEu2094tBL8/iyv92GJbrGHoBDeAzE6reNQD5gZzshwxh"
			+"Zi9arD7A4tDwRLRStHg/D4ySrcPt5YxiaPiLykNCa7c2T5+hj/JzVrTJuf1AreVW"
			+"VcZUNemuFCiw8ozkI/Yoo6hUf4fy6f2KP+ufAgMBAAECggEACS+Ig4H+NMWQ/7vB"
			+"4xsosKnnEa4u1OBVGxHmMqJd5IlFlZp/axbZ+C4N/yNaXT0vMvnBurtqtQpBfuZj"
			+"0KaOVy6lq19x10A13pTQLqX4K0LLZ6rriV+NZQ8qjGvDWCg1hWpbArvWWMLxsumW"
			+"vttqo0gbYQmJaPZRDj/YA+8BvTEWi7Z1m4fC57NnullreBbwywvjmzOObq2+5Tlt"
			+"N/4gdIcmNsvvTCldbQcGOwjOiEWiqmt5poJiuNmrmXkthauUpTk8twRTSDQS0E9b"
			+"sbSa/CvHiriHMnv69lu9sQdhqfMKe9OmlLRDnsgeBrgFpjnv3Rlm6N4I52G1WNze"
			+"eXlO0QKBgQCzt1o69S/Y7jvrS30Y8zT21sTlC6trr79l/r4c9BveKd3NPGBk4FsK"
			+"haTQHkgxrTCjjfyrydYaJeLrf9YR+UegHElzplWP3cctG80xc8Oi8ZmdkkvSNVfr"
			+"uVYNEQwVZucf2hft/VCkWAf7Gf2m8XZum07zzC9/yIuf24hXaY7KNwKBgQCo58DZ"
			+"lTGLufvkeDfdnkGiUBIFGcQAxe3K2g0h18ZAehWjg8AyKrMOot8vW3rGEQ9tPVrT"
			+"Ddq/k0KfFXHYYsnvAonHhVyjYUfa7GhTnhq8YGqmEO8joQ2dTOLpu2LXY8vvW7AD"
			+"u+iGupd6XFEuYq0TMIO545dqesck7HXNI2AV2QKBgEE6nIJnud0n4xGtDZO/uoqZ"
			+"O0/TI33rhTHU+1Xw+Chz07CJaIdUs1anWUG8rAFz1gKXJ42GrbJB7BIEDUPq0ad4"
			+"fOSAC/Ay0/c4bwqy9OAwhswdbG24tNe3JleJx3QBLIRwGV1lIx7zYLowHdO5Mn5E"
			+"2qw8gUUFGkCEYwEvLAeTAoGAEoZ4rGSkroKTkhdtaS02XasSjuSojr0mu5z228Ni"
			+"xyecv/ZvWhE82loayILsEFVGr+CO2Z8nYuOv5urfNbCAHWrV2cBSzMiarPRI7QLm"
			+"Q8r/Z39r+yhq6dnI+YHH8X/MmlAgGgi+FCja/Fyrur6v5O7pQ7YmoVMeDHqy4PEE"
			+"VOECgYEAj8T0L37pOqgdLp6WTxsFPUeT/9C2Jwlct4m8WH3YPZXelgH9Uqs0qtOu"
			+"7ZaUnnzAt0n/wBDcCGsHcy+UInzlt6AexXbCpDgYPnSPCYxfae5NhCcxeDP4QZTg"
			+"dUyzLZRmqit1QBPoQmnLLUQ+ZE8boAVZmOoTptvTvUx+SO+F3y4="
			+"-----END RSA PRIVATE KEY-----";

	
	private String publicKey = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBITANBgkqhkiG9w0BAQEFAAOCAQ4AMIIBCQKCAQB2kwULXu7YGoLdMg0nVSes\r\n"
			+ "oSPUf0tvvyeNYz81H9gCYNG0eELXOJP+luA+MJVIiWAqZ2dXpLSKAdeeX9c1nm4c\r\n"
			+ "luj1HUGi0etD9HDr44BjyUYPv56YvLkvk99OBVVDF1RwvZ0fwlYNekzWkQAse09z\r\n"
			+ "wmpzhBTmhblVtwAKCQ9J0Qr50WCUIXpptWM9o2gY3vgMTP0pEu2094tBL8/iyv92\r\n"
			+ "GJbrGHoBDeAzE6reNQD5gZzshwxhZi9arD7A4tDwRLRStHg/D4ySrcPt5YxiaPiL\r\n"
			+ "ykNCa7c2T5+hj/JzVrTJuf1AreVWVcZUNemuFCiw8ozkI/Yoo6hUf4fy6f2KP+uf\r\n"
			+ "AgMBAAE=\r\n"
			+ "-----END PUBLIC KEY-----";
	   

	@Autowired
	private AuthenticationManager authenticationManager;

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(privateKey);
		converter.setVerifierKey(publicKey);
		return converter;
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

		configurer
				.inMemory()
				.withClient(CLIEN_ID)
				.secret(CLIENT_SECRET)
				.authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)
				.scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
				.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS).
				refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore())
				.authenticationManager(authenticationManager)
				.accessTokenConverter(accessTokenConverter());
	}
}
