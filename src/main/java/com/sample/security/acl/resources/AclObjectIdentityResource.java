/**
 * 
 */
package com.sample.security.acl.resources;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.security.acl.domain.AclObjectIdentity;
import com.sample.security.acl.domain.AclObjectIdentityRepository;

/**
 * @author fabio.moriguchi
 *
 */
@RestController
@RequestMapping("acl/objects")
public class AclObjectIdentityResource {

	@Autowired
	private final AclObjectIdentityRepository repository;

	public AclObjectIdentityResource(AclObjectIdentityRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public Collection<AclObjectIdentity> all() {

		return this.repository.findAll();
	}

}
