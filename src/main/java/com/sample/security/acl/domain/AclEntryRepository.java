/**
 * 
 */
package com.sample.security.acl.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author fabio.moriguchi
 *
 */
@Repository
public interface AclEntryRepository extends JpaRepository<AclEntry, Long> {
	
}
