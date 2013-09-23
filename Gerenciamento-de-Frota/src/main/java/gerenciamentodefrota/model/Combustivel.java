package gerenciamentodefrota.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias( "combustivel" )
@Entity
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Combustivel implements Serializable {
	
	private static final long serialVersionUID = -3520205668087592150L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(unique = true)
	private String descricao;

	@Column
	private BigDecimal preco;

	public Long getId() {
		return id;
	}

	@NotEmpty
	public String getDescricao() {
		return descricao;
	}

	@NotNull
	public BigDecimal getPreco() {
		return preco;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

}
