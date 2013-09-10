package gerenciamentodefrota.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Combustivel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(unique = true)
	@NotEmpty
	private String descricao;

	@Column
	@NotNull
	private BigDecimal preco;

	@SuppressWarnings("unused")
	private Combustivel() {
	}

	public Combustivel(Long id) {
		this.id = id;
	}

	public Combustivel(String descricao, BigDecimal preco) {
		this.descricao = descricao;
		this.preco = preco;
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

}
