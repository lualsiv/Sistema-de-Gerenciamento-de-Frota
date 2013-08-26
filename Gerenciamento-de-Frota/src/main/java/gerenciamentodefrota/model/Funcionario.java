package gerenciamentodefrota.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDateTime;

@Entity
@SequenceGenerator(sequenceName = "SEQ_FUNCIONARIO", name = "SEQ_FUNCIONARIO")
public class Funcionario {

	@Id
	@GeneratedValue(generator = "SEQ_FUNCIONARIO", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(unique = true)
	@NotEmpty
	private String cadastro;
	
	@Column
	@NotEmpty
	private String nome;
	
	@Column
	@NotEmpty
	private String cargo;
	
	@Column
	@NotNull
	private Boolean situacao;
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime dataAdmissao;
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime dataExoneracao;
	
	public Funcionario() {
		this.situacao = true;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCadastro() {
		return cadastro;
	}

	public void setCadastro(String cadastro) {
		this.cadastro = cadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public LocalDateTime getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(LocalDateTime dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public LocalDateTime getDataExoneracao() {
		return dataExoneracao;
	}

	public void setDataExoneracao(LocalDateTime dataExoneracao) {
		this.dataExoneracao = dataExoneracao;
	}
		
}
