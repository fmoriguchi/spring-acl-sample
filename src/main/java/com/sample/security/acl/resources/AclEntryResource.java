/**
 * 
 */
package com.sample.security.acl.resources;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.security.acl.domain.AclEntry;
import com.sample.security.acl.domain.AclEntryRepository;

/**
 * @author fabio.moriguchi
 *
 */
@RestController
@RequestMapping("acl/entries")
public class AclEntryResource {

	@Autowired
	private final AclEntryRepository repository;

	public AclEntryResource(AclEntryRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public Collection<AclEntry> all() {

		return this.repository.findAll();
	}

}
