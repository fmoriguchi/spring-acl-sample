/**
 * 
 */
package com.sample.document.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author fabio.moriguchi
 *
 */
public interface DocumentRepository extends JpaRepository<Document, Long> {

	/**
	 * Sem nennhum tipo de filtro por permissao
	 */
	Page<Document> findAll(Pageable pageable);
	
	/**
	 * Filtro de permissao utilizando joins com as tabelas do Spring ACL
	 * @param pageable
	 * @return
	 */
	//@Query("select d from Document d, AclEntry p inner join p.objectId as o where o.objectId = d.id and o.owner.sid = 'japa' and o.clazz.name = 'com.sample.document.domain.Document' and p.mask = '1'")
	//Page<Document> findAllWithReadPermission(Pageable pageable);
	
	
	/**
	 * Filtro de permissao utilizando joins com as tabelas do Spring ACL
	 * @param pageable
	 * @param username
	 * @param domainObjectName
	 * @return
	 */
	//@Query("select d from #{#entityName} d, AclEntry p inner join p.objectId as o where o.objectId = d.id and o.owner.sid = :u and o.clazz.name = :d and p.mask = '1'")
	//Page<Document> findAllWithReadPermission(Pageable pageable, @Param("u") String username, @Param("d") String domainObjectName);
}
