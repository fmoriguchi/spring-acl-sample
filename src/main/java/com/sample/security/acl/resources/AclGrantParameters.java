/**
 * 
 */
package com.sample.security.acl.resources;

import javax.validation.constraints.NotNull;

import com.sample.security.acl.AclPermission;

/**
 * @author fabio.moriguchi
 *
 */
public final class AclGrantParameters {

	@NotNull
	private String domainType;
	
	@NotNull
	private Long domainId;
	
	@NotNull
	private String favored;
	
	private AclPermission permission;

	public String getDomainType() {
		return domainType;
	}

	public void setDomainType(String domainType) {
		this.domainType = domainType;
	}

	public Long getDomainId() {
		return domainId;
	}

	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}
	
	public String getFavored() {
		return favored;
	}

	public void setFavored(String favored) {
		this.favored = favored;
	}

	public AclPermission getPermission() {
		return permission;
	}

	public void setPermission(AclPermission permission) {
		this.permission = permission;
	}
}
