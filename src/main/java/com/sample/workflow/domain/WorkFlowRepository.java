/**
 * 
 */
package com.sample.workflow.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author fabio.moriguchi
 *
 */
@Repository
public interface WorkFlowRepository extends JpaRepository<WorkFlow, Long> {

}
