/**
 * 
 */
package com.sample.security.acl;

import javax.sql.DataSource;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.acls.AclPermissionEvaluator;
import org.springframework.security.acls.domain.AclAuthorizationStrategy;
import org.springframework.security.acls.domain.AclAuthorizationStrategyImpl;
import org.springframework.security.acls.domain.AuditLogger;
import org.springframework.security.acls.domain.ConsoleAuditLogger;
import org.springframework.security.acls.domain.DefaultPermissionGrantingStrategy;
import org.springframework.security.acls.domain.EhCacheBasedAclCache;
import org.springframework.security.acls.jdbc.BasicLookupStrategy;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.jdbc.LookupStrategy;
import org.springframework.security.acls.model.AclCache;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.PermissionGrantingStrategy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.sample.security.permissions.ChainPermissionsEvaluator;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

/**
 * @author fabio.moriguchi
 *
 */
@Configuration
@EnableCaching
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class AclConfiguration {

	private static final String ACL_CACHE = "aclCache";

	@Bean
	AclAuthorizationStrategy aclAuthorizationStrategy() {

		return new AclAuthorizationStrategyImpl(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Bean
	PermissionGrantingStrategy permissionGrantingStrategy() {

		AuditLogger logger = new ConsoleAuditLogger();

		return new DefaultPermissionGrantingStrategy(logger);
	}

	@Bean
	Ehcache ehCache() {
		
		CacheManager cacheManager = CacheManager.create();
		
		if (!cacheManager.cacheExists(ACL_CACHE)) {
			cacheManager.addCache(ACL_CACHE);
		}

		return cacheManager.getEhcache(ACL_CACHE);
	}

	@Bean
	AclCache aclCache(Ehcache cache, PermissionGrantingStrategy grantingStrategy, AclAuthorizationStrategy aclAuthorizationStrategy) {

		return new EhCacheBasedAclCache(cache, grantingStrategy, aclAuthorizationStrategy);
	}

	@Bean
	MutableAclService mutableAclService(LookupStrategy lookupStrategy, AclCache aclCache, DataSource dataSource) {

		JdbcMutableAclService service = new JdbcMutableAclService(dataSource, lookupStrategy, aclCache);
		service.setClassIdentityQuery("select currval(pg_get_serial_sequence('acl_class', 'id'))");
		service.setSidIdentityQuery("select currval(pg_get_serial_sequence('acl_sid', 'id'))");

		return service;
	}

	@Bean
	LookupStrategy lookupStrategy(DataSource dataSource, AclCache aclCache, AclAuthorizationStrategy aclAuthorizationStrategy, PermissionGrantingStrategy grantingStrategy) {

		return new BasicLookupStrategy(dataSource, aclCache, aclAuthorizationStrategy, grantingStrategy);
	}

	@Bean
	MethodSecurityExpressionHandler expressionHandler(PermissionEvaluator evaluator) {

		DefaultMethodSecurityExpressionHandler securityExpression = new DefaultMethodSecurityExpressionHandler();
		securityExpression.setPermissionEvaluator(evaluator);

		return securityExpression;
	}

	@Bean
	PermissionEvaluator permissionEvaluator(MutableAclService aclService) {

		//return new AclPermissionEvaluator(aclService);
		return ChainPermissionsEvaluator.buildNewInstance(new AclPermissionEvaluator(aclService));
	}
}
