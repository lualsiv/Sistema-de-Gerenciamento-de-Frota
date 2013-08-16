package gerenciamentodefrota.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.DecimalMin;

@Entity
@SequenceGenerator(sequenceName = "SEQ_COMBUSTIVEL", name = "SEQ_COMBUSTIVEL")
public class Combustivel {

	@Id
	@GeneratedValue(generator = "SEQ_COMBUSTIVEL", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(unique = true)
	private String descricao;

	@Column
	@DecimalMin("0.01")
	private BigDecimal valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
}
