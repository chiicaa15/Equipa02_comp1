package equipa2;

/**
 * 
 */
import jakarta.persistence.*; 
/**
 * 
 */
@Entity 
@DiscriminatorValue("Student")
public class Student extends User {
	private int num;
	
	
	public Student() {
		
	}
	
	public Student(String nome, String email, String password, int num) {
		super(nome, email, password);
		this.num = num;
	}

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}

	@Override
	public String toString() {
		return super.toString() + ", Student [num=" + num + "]";
	}
	
	

}

