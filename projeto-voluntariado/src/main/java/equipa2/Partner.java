package equipa2;
/**
 * 
 */

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

/**
 * 
 */
@Entity
@Table(name="Partners")
public class Partner {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable = false)
	private String partner;
	
	@OneToMany(mappedBy= "partner", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Program>programas= new ArrayList<Program>();

	/**
	 * @param namePartner
	 */
	public Partner(String partner) {
		this.partner = partner;
	}
	
	public Partner() {
		
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param namePartner the namePartner to set
	 */
	public void setPartner(String partner) {
		this.partner = partner;
	}

	public List<Program> getProgramas(){
		return programas;
	}
	
	public void adicionarPrograma(Program novoPrograma) {
		programas.add(novoPrograma);
		novoPrograma.setPartner(this);
	}

	/**
	 * @return the namePartner
	 */
	public String getPartner() {
		return partner;
	}

	@Override
	public String toString() {
		return "Partner [id=" + id + ", Partner=" + partner + "]";
	}
	
	
}