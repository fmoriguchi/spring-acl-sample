/**
 * 
 */
package com.sample.security.acl;

import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.model.Permission;

/**
 * @author fabio.moriguchi
 *
 */
public enum AclPermission {

	READ {
		@Override
		public Permission permission() {
			return BasePermission.READ;
		}
	},

	WRITE {
		@Override
		public Permission permission() {
			return BasePermission.WRITE;
		}
	},
	
	CREATE {
		@Override
		public Permission permission() {
			return BasePermission.CREATE;
		}
	},
	
	DELETE {
		@Override
		public Permission permission() {
			return BasePermission.DELETE;
		}
	},
	
	ADMINISTRATION {
		@Override
		public Permission permission() {
			return BasePermission.ADMINISTRATION;
		}
	};


	public abstract Permission permission();
}
