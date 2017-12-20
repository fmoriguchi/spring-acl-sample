/**
 * 
 */
package com.sample.security.acl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.AccessControlEntry;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;

/**
 * @author fabio.moriguchi
 *
 */
public abstract class AbstractAclPermissions implements AclPermissions {

	private final MutableAclService aclRepository;

	public AbstractAclPermissions(MutableAclService acl) {
		this.aclRepository = acl;
	}

	@Override
	public void add(String favored, AclDomain domainObject, AclPermission permission) {
		
		this.add(favored, domainObject, permission.permission());
	}
	

	@Override
	public void add(String favored, AclDomain domainObject) {
		
		Arrays.stream(AclPermission.values())
			  .forEach(p -> add(favored, domainObject, p.permission()));
	}
	
	@Override
	public void remove(String favored, AclDomain domainObject, AclPermission permission) {
		this.remove(favored, domainObject, permission.permission());
		
	}

	@Override
	public void remove(String favored, AclDomain domainObject) {
		
		Arrays.stream(AclPermission.values())
			  .forEach(p -> remove(favored, domainObject, p.permission()));
	}
	
	
	protected abstract Sid createSid(String favored);
	
	private void add(String favored, Object domainObject, Permission... permissions) {

		ObjectIdentity identity = new ObjectIdentityImpl(domainObject);

		Optional<MutableAcl> optAcl = this.findBy(identity);

		MutableAcl acl = optAcl.isPresent() ? optAcl.get() : this.createAcl(identity);

		Sid sid = createSid(favored);

		for (Permission permission : permissions) {
			acl.insertAce(acl.getEntries().size(), permission, sid, true);
		}

		aclRepository.updateAcl(acl);
	}

	private void add(String favored, AclDomain domainObject, Permission... permissions) {

		this.add(favored, (Object) domainObject, permissions);
	}

	private void remove(String favored, Object domainObject, Permission... permissions) {

		Optional<MutableAcl> acl = findBy(new ObjectIdentityImpl(domainObject));

		acl.ifPresent(o -> remove(favored, o, permissions[0]));

		acl.ifPresent(o -> aclRepository.updateAcl(o));
	}

	private MutableAcl remove(String favored, MutableAcl acl, Permission permission) {

		final Sid sid = createSid(favored);

		final List<AccessControlEntry> accessControls = acl.getEntries();

		for (int i = 0; i < accessControls.size(); i++) {

			AccessControlEntry entry = accessControls.get(i);
			
			if (hasPermission(sid, permission, entry)) {
				
				acl.deleteAce(i);
			}
		}

		return acl;
	}
	
	private Boolean hasPermission(Sid sid, Permission permission, AccessControlEntry entry) {
		
		return entry.getSid().equals(sid) && entry.getPermission().equals(permission);
	}

	private Optional<MutableAcl> findBy(ObjectIdentity identity) {

		try {
			return Optional.ofNullable((MutableAcl) aclRepository.readAclById(identity));
		} catch (NotFoundException e) {
			return Optional.empty();
		}
	}

	private MutableAcl createAcl(ObjectIdentity identity) {

		return this.aclRepository.createAcl(identity);
	}
}
