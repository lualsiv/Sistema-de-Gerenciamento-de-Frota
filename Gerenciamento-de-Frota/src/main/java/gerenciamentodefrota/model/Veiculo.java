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
	
	@Column(unique = true, length = 8)
	@NotEmpty
	private String placa;
	
	@Column
	@NotEmpty
	private String descricao;
	
	@Column
	@NotEmpty
	private String marca;
	
	@Column
	@NotEmpty
	private String modelo;
	
	@Column
	@NotEmpty
	private String cor;
	
	@Column
	@NotNull
	private Integer anoFabricacao;

	@Column
	@NotNull
	private Integer anoModelo;

	@Column
	@NotEmpty
	private String chassi;

	@Column
	@NotEmpty
	private String renavam;
	
	@Column
	private String observacao;
	
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
		this.placa = placa.toUpperCase();
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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getRenavam() {
		return renavam;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
}
