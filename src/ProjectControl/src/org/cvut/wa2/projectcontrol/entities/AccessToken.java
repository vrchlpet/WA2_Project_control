package org.cvut.wa2.projectcontrol.entities;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class AccessToken {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key accessTokenKey;
	
	@Persistent
	private TokenType tokenType;
	
	@Persistent
	private String tokenText;

	public AccessToken() {}
	
	public AccessToken(Key accessTokenKey, TokenType tokenType, String tokenText) {
		super();
		this.accessTokenKey = accessTokenKey;
		this.tokenType = tokenType;
		this.tokenText = tokenText;
	}

	public Key getAccessTokenKey() {
		return accessTokenKey;
	}

	public void setAccessTokenKey(Key accessTokenKey) {
		this.accessTokenKey = accessTokenKey;
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	public void setTokenType(TokenType tokenType) {
		this.tokenType = tokenType;
	}

	public String getTokenText() {
		return tokenText;
	}

	public void setTokenText(String tokenText) {
		this.tokenText = tokenText;
	}
	
	
	
	
}
