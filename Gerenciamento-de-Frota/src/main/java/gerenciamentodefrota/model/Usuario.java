package gerenciamentodefrota.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

@Entity
@SequenceGenerator(sequenceName = "SEQ_USUARIO", name = "SEQ_USUARIO")
public class Usuario {

	@Id
	@GeneratedValue(generator = "SEQ_USUARIO", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(unique = true)
	@NotEmpty
	private String login;
	
	@NotEmpty
	private String senha;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private Perfil perfil;
	
	@ManyToOne
	private Funcionario funcionario;

	@NotNull
	private Boolean situacao;	
	
	@NotNull
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime dataCadastro;
	
	public Usuario() {
		this.situacao = true;
		this.dataCadastro = DateTime.now();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}

	public DateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(DateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
}
