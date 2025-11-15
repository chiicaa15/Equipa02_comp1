package equipa2;

/**
 * 
 */
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 */
@Entity
@Table(name="Tipos")
public class Type {
	
	@Id 
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column (nullable = false)
	private String type;
	
	@OneToMany(mappedBy= "type", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Program>programas= new ArrayList<Program>();
	
	/**
	 * @param type
	 */
	public Type(String type) {
		this.type = type;
	}
	
	public Type() {
		
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	public void setType(String type){
		this.type=type;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	public List<Program> getProgramas(){
		return programas;
	}
	
	public void adicionarPrograma(Program novoPrograma) {
		programas.add(novoPrograma);
		novoPrograma.setType(this);
	}

	@Override
	public String toString() {
		return "Type [id=" + id + ", type=" + type + "]";
	}
	
	
}
