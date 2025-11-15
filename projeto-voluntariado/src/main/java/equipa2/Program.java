package equipa2;
/**
 * 
 */

import jakarta.persistence.*; 
/**
 * 
 */
@Entity
@Table(name="Programas")
public class Program {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nomeP;
	private String description;
	private String location;
	private int contact;
	
	@ManyToOne
	@JoinColumn(name="tipo_id", nullable = false) //foreign key
	private Type type;
	
	@ManyToOne
	@JoinColumn(name="partner_id", nullable= false)
	private Partner partner;
	private int vagas;

	public Program() {
		
	}
	
	public Program(String nomeP, String description, String location, int contact, int vagas) {
		this.nomeP = nomeP;
		this.description = description;
		this.location = location;
		this.contact = contact;
		this.vagas= vagas;
	}
	
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @return the vagas
	 */
	public int getVagas() {
		return vagas;
	}

	/**
	 * @param vagas the vagas to set
	 */
	public void setVagas(int vagas) {
		this.vagas = vagas;
	}


	/**
	 * @return the nomeP
	 */
	public String getNomeP() {
		return nomeP;
	}
	
	/**
	 * @param nomeP the nomeP to set
	 */
	public void setNomeP(String nomeP) {
		this.nomeP = nomeP;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * @return the contact
	 */
	public int getContact() {
		return contact;
	}
	
	/**
	 * @param contact the contact to set
	 */
	public void setContact(int contact) {
		this.contact = contact;
	}
	
	
	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}
	
	public Partner getPartner() {
		return partner;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}
	
	public void setPartner(Partner partner) {
		this.partner= partner;
	}

	@Override
	public String toString() {
		return "Program [id=" + id + ", nomeP=" + nomeP + ", description=" + description + ", location=" + location
				+ ", contact=" + contact + ", vagas=" + vagas + ", type=" + type +", partner= "+partner+ "]";
	}

}

