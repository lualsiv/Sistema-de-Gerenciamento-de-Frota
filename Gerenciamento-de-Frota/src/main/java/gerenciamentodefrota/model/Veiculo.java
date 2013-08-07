package gerenciamentodefrota.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(sequenceName = "SEQ_VEICULO", name = "SEQ_VEICULO")
public class Veiculo {

	@Id
	@GeneratedValue(generator = "SEQ_VEICULO", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(unique = true)
	private String placa;
	
	@Column
	private Integer anoFabricacao;
	
	@Column
	private Integer anoModelo;
	
	
	
}
