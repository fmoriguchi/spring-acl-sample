/**
 * 
 */
package com.sample.workflow.resources;

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

import com.sample.workflow.domain.WorkFlow;
import com.sample.workflow.domain.WorkFlowRepository;

/**
 * @author fabio.moriguchi
 *
 */
@RestController
@RequestMapping("workflows")
public class WorkFlowResource {

	private final WorkFlowRepository repository;

	@Autowired
	public WorkFlowResource(WorkFlowRepository repository) {
		this.repository = repository;
	}

	
	@PostMapping
	public WorkFlow create(@RequestBody WorkFlow workFlow) {
		
		return repository.save(workFlow);
	}
	
	
	//@PreAuthorize("hasPermission(#id, 'com.sample.workflow.domain.WorkFlow', 'read')")
	@PostAuthorize("hasPermission(returnObject, 'READ')")
	@GetMapping("{id}")
	public WorkFlow find(@PathVariable("id") Long id) {
		
		return repository.findOne(id);
	}
	
	
	//@ReadOnlyAllowedObjects
	@PostFilter("hasPermission(filterObject, 'read')")
	@GetMapping
	public Collection<WorkFlow> all() {
		
		return repository.findAll();
	}
	
	@PreAuthorize("hasPermission(#workFlow, 'write')")
	@PutMapping
	public WorkFlow update(@RequestBody WorkFlow workFlow) {
		
		if (workFlow.getId() == null){
			throw new IllegalArgumentException("Cannot update workflow with null id!!");
		}
		
		return repository.save(workFlow);
	}
}
