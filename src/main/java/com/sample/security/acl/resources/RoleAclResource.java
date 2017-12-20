package com.sample.security.acl.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.security.acl.AclDomain;
import com.sample.security.acl.AclPermissions;

/**
 * 
 * Api para conceder permissao para um registro atraves de ACL (Access Control List)
 * 
 * @author fabio.moriguchi
 *
 */
@Transactional
@RestController
@RequestMapping("acls/role")
public class RoleAclResource {

	private final AclPermissions permissions;

	@Autowired
	public RoleAclResource(@Qualifier("acl.permissions.role") AclPermissions permissions) {
		this.permissions = permissions;
	}
	
	@PostMapping
	public void grantPermission(@Valid @RequestBody AclGrantParameters grantParameters) {
		
		AclDomain domainObject = AclHelper.createAclDomainObject(grantParameters);

		permissions.add(grantParameters.getFavored(), domainObject, grantParameters.getPermission());
	}
	
	@PostMapping("all")
	public void grantAllPermissions(@Valid @RequestBody AclGrantParameters grantParameters) {

		AclDomain domainObject = AclHelper.createAclDomainObject(grantParameters);

		permissions.add(grantParameters.getFavored(), domainObject);
	}

	@DeleteMapping
	public void removePermission(@Valid @RequestBody AclGrantParameters grantParameters) {

		AclDomain domainObject = AclHelper.createAclDomainObject(grantParameters);

		permissions.remove(grantParameters.getFavored(), domainObject, grantParameters.getPermission());
	}
	
	@DeleteMapping("all")
	public void removeAllPermission(@Valid @RequestBody AclGrantParameters grantParameters) {

		AclDomain domainObject = AclHelper.createAclDomainObject(grantParameters);

		permissions.remove(grantParameters.getFavored(), domainObject);
	}
	
}
