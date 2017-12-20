/**
 * 
 */
package com.sample.security.permissions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

/**
 * @author fabio.moriguchi
 *
 */
public final class ChainPermissionsEvaluator implements PermissionEvaluator {

	private final List<PermissionEvaluator> evaluators = new ArrayList<>();

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {

		return evaluators.stream().anyMatch(o -> o.hasPermission(authentication, targetDomainObject, permission));
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {

		return evaluators.stream().anyMatch(o -> o.hasPermission(authentication, targetId, targetType, permission));
	}

	public ChainPermissionsEvaluator add(PermissionEvaluator permissionEvaluator) {

		evaluators.add(permissionEvaluator);

		return this;
	}

	public static PermissionEvaluator buildNewInstance(PermissionEvaluator... permissions) {

		ChainPermissionsEvaluator evaluator = new ChainPermissionsEvaluator();

		for (PermissionEvaluator permission : permissions) {
			evaluator.add(permission);
		}

		return evaluator;
	}
}
