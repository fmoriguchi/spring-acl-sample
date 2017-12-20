/**
 * 
 */
package com.sample.security.acl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.Sid;
import org.springframework.stereotype.Component;

/**
 * @author fabio.moriguchi
 *
 */
@Component("acl.permissions.user")
public final class UserAclPermissions extends AbstractAclPermissions implements AclPermissions {

	@Autowired
	public UserAclPermissions(MutableAclService acl) {
		super(acl);
	}

	@Override
	protected Sid createSid(String username) {
		
		return new PrincipalSid(username);
	}
}
