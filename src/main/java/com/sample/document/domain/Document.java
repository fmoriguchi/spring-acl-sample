/**
 * 
 */
package com.sample.document.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.sample.security.acl.AclDomain;

/**
 * @author fabio.moriguchi
 *
 */
@Entity
public class Document implements AclDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title;

	private String content;

	public Document() {
	}

	public Document(Long id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public Document(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public Document(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
		Document other = (Document) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public static void main(String[] args) {

		Document document = new Document(600l, "Bom dia", "Suco de caju");

		ExpressionParser expressionParser = new SpelExpressionParser();
		Expression expression = expressionParser.parseExpression("(id + id) * 2 ");

		EvaluationContext context = new StandardEvaluationContext(document);
		String result = expression.getValue(context, String.class);

		System.out.println(result);

		/*
		 * System.out.println(1 << 0); System.out.println(1 << 1);
		 * System.out.println(1 << 2); System.out.println(1 << 3);
		 * System.out.println(1 << 4); System.out.println(1 << 5);
		 * System.out.println(1 << 6); System.out.println(1 << 7);
		 * System.out.println(1 << 8); System.out.println(1 << 9);
		 * System.out.println(1 << 10); System.out.println(1 << 11);
		 * System.out.println(1 << 12);
		 */

	}
}
