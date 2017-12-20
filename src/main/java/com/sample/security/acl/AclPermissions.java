package com.sample.security.acl;

/**
 * 
 * @author fabio.moriguchi
 *
 */
public interface AclPermissions {

	void add(String favored, AclDomain domainObject, AclPermission permission);
	
	void add(String favored, AclDomain domainObject);
	
	void remove(String favored, AclDomain domainObject, AclPermission permission);
	
	void remove(String favored, AclDomain domainObject);
}