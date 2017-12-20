/**
 * 
 */
package com.sample.security.acl.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author fabio.moriguchi
 *
 */
@Entity
@Table(name = "acl_object_identity")
public class AclObjectIdentity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "object_id_identity")
	private Long objectId;

	@Transient
	@ManyToOne
	@JoinColumn(name = "object_id_class")
	private AclClass clazz;

	@Transient
	@ManyToOne
	@JoinColumn(name = "owner_sid")
	private AclSid owner;

	@Transient
	@ManyToOne
	@JoinColumn(name = "parent_object")
	private AclObjectIdentity parent;

	@Transient
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name = "ID")
	private Collection<AclEntry> entries;

	@Column(name = "entries_inheriting")
	private Boolean entriesInheriting;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AclClass getClazz() {
		return clazz;
	}

	public void setClazz(AclClass clazz) {
		this.clazz = clazz;
	}

	public AclSid getOwner() {
		return owner;
	}

	public void setOwner(AclSid owner) {
		this.owner = owner;
	}

	public AclObjectIdentity getParent() {
		return parent;
	}

	public void setParent(AclObjectIdentity parent) {
		this.parent = parent;
	}

	public Boolean getEntriesInheriting() {
		return entriesInheriting;
	}

	public void setEntriesInheriting(Boolean entriesInheriting) {
		this.entriesInheriting = entriesInheriting;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
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
		AclObjectIdentity other = (AclObjectIdentity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
