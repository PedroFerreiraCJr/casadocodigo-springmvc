package br.com.dotofcodex.casadocodigo.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.com.dotofcodex.casadocodigo.type.BookType;

@Embeddable
public class Price {

	@Column(scale = 2)
	private BigDecimal value;
	private BookType type;

	public Price() {
		super();
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BookType getType() {
		return type;
	}

	public void setType(BookType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Price [value=").append(value).append(", type=").append(type).append("]");
		return builder.toString();
	}

}
