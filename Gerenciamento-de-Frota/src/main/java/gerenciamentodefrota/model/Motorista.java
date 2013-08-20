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

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@SequenceGenerator(sequenceName = "SEQ_MOTORISTA", name = "SEQ_MOTORISTA")
public class Motorista {

	@Id
	@GeneratedValue(generator = "SEQ_MOTORISTA", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column
	@NotEmpty String numero;
	
	@Column
	@NotEmpty
	private String registro;
	
	@Enumerated(EnumType.STRING)
	private CategoriaCNH categoria;
	
	@ManyToOne
	private Funcionario funcionario;
	
	@Column
	@NotNull
	private Boolean situacao;
	
	@Column
	private String observacao;
	
	public Motorista() {
		this.situacao = true;
	}
	
}
