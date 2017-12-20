/**
 * 
 */
package com.sample.security.acl.resources;

import com.sample.security.acl.AclDomain;

/**
 * @author fabio.moriguchi
 *
 */
public final class AclHelper {

	private AclHelper(){}
	
	public static AclDomain createAclObject(String domainClassName, Long id) {

		try {
			Class<?> domainClass = Class.forName(domainClassName);
			AclDomain domainObject = (AclDomain) domainClass.newInstance();
			domainObject.setId(id);
			return domainObject;

		} catch (Exception e) {
			throw new IllegalArgumentException("Cannot generate Acl domain object by class " + domainClassName + ". Reason: " + e.getMessage(), e);
		}
	}
	
	public static AclDomain createAclDomainObject(AclGrantParameters parameters) {

		return createAclObject(parameters.getDomainType(), parameters.getDomainId());
	}
}
