package gerenciamentodefrota.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@SequenceGenerator(sequenceName = "SEQ_VEICULO", name = "SEQ_VEICULO")
public class Veiculo {

	@Id
	@GeneratedValue(generator = "SEQ_VEICULO", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(unique = true)
	@NotNull
	@NotEmpty
	private String placa;
	
	@Column
	@NotNull
	@NotEmpty
	private Integer anoFabricacao;
	
	@Column
	@NotNull
	@NotEmpty
	private Integer anoModelo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Integer getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(Integer anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public Integer getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(Integer anoModelo) {
		this.anoModelo = anoModelo;
	}
	
}
