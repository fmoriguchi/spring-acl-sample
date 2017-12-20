/**
 * 
 */
package com.sample.security.acl.custom;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PostFilter;

/**
 * @author fabio.moriguchi
 *
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@PostFilter("hasPermission(filterObject, 'read')")
public @interface ReadOnlyAllowedObjects {

}
