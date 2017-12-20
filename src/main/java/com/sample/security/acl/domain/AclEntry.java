/**
 * 
 */
package com.sample.security.acl.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author fabio.moriguchi
 *
 */
@Entity
@Table(name = "acl_entry")
public class AclEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "sid")
	private AclSid sid;

	@ManyToOne
	@JoinColumn(name = "acl_object_identity")
	private AclObjectIdentity objectId;

	@Column(name = "ace_order")
	private Integer order;

	@Column(name = "mask")
	private Integer mask;

	@Column(name = "granting")
	private Boolean granting;

	@Column(name = "audit_success")
	private Boolean auditSuccess;

	@Column(name = "audit_failure")
	private Boolean auditFailure;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AclSid getSid() {
		return sid;
	}

	public void setSid(AclSid sid) {
		this.sid = sid;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getMask() {
		return mask;
	}

	public void setMask(Integer mask) {
		this.mask = mask;
	}

	public Boolean getGranting() {
		return granting;
	}

	public void setGranting(Boolean granting) {
		this.granting = granting;
	}

	public Boolean getAuditSuccess() {
		return auditSuccess;
	}

	public void setAuditSuccess(Boolean auditSuccess) {
		this.auditSuccess = auditSuccess;
	}

	public Boolean getAuditFailure() {
		return auditFailure;
	}

	public void setAuditFailure(Boolean auditFailure) {
		this.auditFailure = auditFailure;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AclEntry other = (AclEntry) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
