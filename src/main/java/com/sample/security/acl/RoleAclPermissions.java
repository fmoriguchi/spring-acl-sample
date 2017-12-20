/**
 * 
 */
package com.sample.security.acl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.Sid;
import org.springframework.stereotype.Component;

/**
 * @author fabio.moriguchi
 *
 */
@Component("acl.permissions.role")
public class RoleAclPermissions extends AbstractAclPermissions implements AclPermissions {

	@Autowired
	public RoleAclPermissions(MutableAclService acl) {
		super(acl);
	}
	
	@Override
	protected Sid createSid(String favored) {
		return new GrantedAuthoritySid(favored);
	}
}
