package com.auth.dbauth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class JWTBlackListTokenEntity {
	@Id
	@Column(name = "jwtid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jwtid;

	@Column(name = "jwt_token")
	@Lob
	private String token;

	public Long getjwtid() {
		return jwtid;
	}

	public void set_id(Long jwtid) {
		this.jwtid = jwtid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "JwtBlacklist{" + "_id='" + jwtid + '\'' + ", token='" + token + '\'' + '}';
	}
}
