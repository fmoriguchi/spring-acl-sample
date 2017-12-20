/**
 * 
 */
package com.sample.document.interfaces;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.document.domain.Document;
import com.sample.document.domain.DocumentRepository;

/**
 * @author fabio.moriguchi
 *
 */
@RestController
@RequestMapping("documents")
public class DocumentResource {

	private final DocumentRepository repository;

	@Autowired
	public DocumentResource(DocumentRepository repository) {
		this.repository = repository;
	}

	@PostMapping
	public Document create(@RequestBody Document document) {

		return this.repository.save(document);
	}

	
	
	
	
	/**
	 * Exemplo buscando um documento avaliando se o usuario que esta realizando
	 * a requisicao tem ou nao permissao de acesso para visualizar o objeto
	 * 
	 * @param id
	 * @return
	 */
	//@PreAuthorize("hasPermission(#id, 'com.sample.document.domain.Document', 'read')")	//@CanReadDocument
	@PostAuthorize("hasPermission(returnObject, 'READ')")
	@GetMapping("{id}")
	public Document find(@PathVariable("id") Long id) {

		return this.repository.findOne(id);
	}

	
	
	
	
	/**
	 * Aqui avaliamos se o usuario logado pode alterar o documento
	 * 
	 * @param document
	 * @return
	 */
	@PreAuthorize("hasPermission(#document, 'write')") //@CanUpdateDocument
	@PutMapping
	public Document update(@RequestBody Document document) {
		
		return this.repository.save(document);
	}

	
	
	
	
	/**
	 * Aqui avaliamos quais registros retornados na lista de documentos o
	 * usuario logado tem permissao para visualizacao
	 * 
	 * @return
	 */
	@PostFilter("hasPermission(filterObject, 'read')") //@ReadOnlyAllowedObjects
	@GetMapping
	public Collection<Document> all() {

		return this.repository.findAll();
	}
}
