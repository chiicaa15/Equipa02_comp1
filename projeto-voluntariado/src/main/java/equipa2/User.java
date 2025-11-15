/**
 * 
 */
package equipa2;
import jakarta.persistence.*; 
/**
 * 
 */
@Entity
@Table (name="users")
@Inheritance (strategy = InheritanceType.SINGLE_TABLE) 
@DiscriminatorColumn (name = "user_type", discriminatorType = 
DiscriminatorType.STRING) 
@DiscriminatorValue("Manager")
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column (name="user_id")
	private long id;
	
	private String name;
	@Column (nullable = false)
	private String email;
	@Column (nullable = false)
	private String password;
	/**
	 * @param nome
	 * @param email
	 * @param password
	 */
	
	public User() {
		
	}
	
	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the name
	 */
	public String getNome() {
		return name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	
	
	
}
