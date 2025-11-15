package equipa2;

/**
 * 
 */
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session; 
import org.hibernate.SessionFactory; 
import org.hibernate.boot.MetadataSources; 
import org.hibernate.boot.registry.StandardServiceRegistry; 
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
/**
 * 
 */
public class ProgramManager {
	public SessionFactory sessionFactory;
	public ArrayList<User>users= new ArrayList();
	public ArrayList<Program>programs=new ArrayList();
	
	public ProgramManager() {
		
	}
	
	public void setup() {
		 sessionFactory = HibernateUtil.getSessionFactory(); //Inicializar ligação com base de dados
	}
	
	public User loginUtilizador(String email, String pass) {
		User user = null;

	    try (Session session = sessionFactory.openSession()) {
	        session.beginTransaction();

	        Query<User> query = session.createQuery(
	            "FROM User WHERE email = :email AND password = :pass", User.class);
	        query.setParameter("email", email);
	        query.setParameter("pass", pass);

	        List<User> users = query.list();
	        if (!users.isEmpty()) {
	            user = users.get(0);
	        }

	        session.getTransaction().commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return user;
	}

	
	//Método para persistir user
	public void saveUser(User user) {
		Transaction tx = null;
        try (Session session = sessionFactory.openSession()) { //Abrir ligação com a base de dados
            tx = session.beginTransaction(); // Iniciar transação 	
            session.persist(user); // persiste user
            tx.commit(); 
            System.out.println("Utilizador registado com sucesso: " + user);
        } catch (Exception e) {
            if (tx != null) tx.rollback(); // faz um rollback se algo falhar
            e.printStackTrace();
        }
	}
	
	//Método para persistir o tipo de programa
	public void saveType(Type type) {
		Transaction tx = null;
        try (Session session = sessionFactory.openSession()) { // Abrir ligação com base de dados
            tx = session.beginTransaction();
            session.persist(type);
            tx.commit();
            System.out.println("Tipo de Programa registado: " + type);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
	}
	
	//Método para adicionar um novo User
	public void adicionarUser(User novoUser) {
		
		if (!validarEmail(novoUser.getEmail())) { //se o email fr diferente da verificação do método dá erro
			return;
		}
		
		else if (!validarPalavraPasse(novoUser.getPassword())) {  //se a palavra-passe for diferente da verificação do método dá erro
			return;
		}
		
		else {
			users.add(novoUser);
			saveUser(novoUser); //Chama o método saveUser para salvar user na base de dados
		}
		
		
	}
	
	//Método para adicionar um novo estudante
	public void adicionarStudent(Student novoStudent) {
		
		if (!validarEmail(novoStudent.getEmail())) {  //se o email fr diferente da verificação do método dá erro
			return;
		}
		
		else if (!validarPalavraPasse(novoStudent.getPassword())) {  //se a palavra-passe for diferente da verificação do método dá erro
			return;
		}
		
		else {
			users.add(novoStudent);
			saveUser(novoStudent);//Chama o método saveUser para salvar o estudante na base de dados
		}
		
	}
	
	//Método para adicionar um tipo
	public void adicionarType(Type type) {
		saveType(type); // Chama o método saveType para salvar o tipo na base de dados
	}
	
	//Método para imprimir Tipos de programa
	public void printType() {
		 try (Session session = sessionFactory.openSession()) {
		        session.beginTransaction();

		        System.out.println("Lista de Tipos de Programa:");
		        List<Type> tipos = session.createQuery("from Type", Type.class).list();// criar um query(questionar base de dados) para selecionar todos os objetos da classe Type
		        for (Type t : tipos) {
		            System.out.println(t);// ciclo for para obter cada objeto
		        }
		        session.getTransaction().commit();
		    } catch (Exception e) { // exceção de erro
		        e.printStackTrace();
		    }
		 }
	
	//Método para pesquisar
	public void pesquisarTipo(String tipo) {
		try (Session session = sessionFactory.openSession()) {
	        session.beginTransaction();

	        Query<Type> query = session.createQuery("from Type t where t.type = :name", Type.class);// 
	        query.setParameter("name", tipo);
	        List<Type> result = query.list(); //executa o query e devolve a lista de objetos encontrados na classe Type
	        for (Type t : result) {
	            System.out.println(t);
	        }

	        session.getTransaction().commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	//Método para imprimir utilizadores
	public void printUsers() {
		try (Session session= sessionFactory.openSession()){
			session.beginTransaction();

	        System.out.println("Lista de Tipos de Utilizadores:");
	        List<User> users = session.createQuery("from User", User.class).list();// criar um query(questionar base de dados) para selecionar todos os objetos da classe User
	        for (User u: users) { //ciclo for para imprimir um user de cada vez
	        	System.out.println(u);
	        }
	        session.getTransaction().commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	//Método para criar Programa
	public void criarPrograma(Program p, String typeName, String partnerName) {
	    Transaction tx = null;

	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        tx = session.beginTransaction();

	        // Procurar Partner
	        Query<Partner> parceiro = session.createQuery("from Partner where partner = :name", Partner.class);
	        parceiro.setParameter("name", partnerName);

	        Partner partner;
	        List<Partner> partners = parceiro.getResultList();
	        if (partners.isEmpty()) {
	            partner = new Partner();
	            partner.setPartner(partnerName);
	            session.persist(partner);
	        } else {
	            partner = partners.get(0);
	        }

	        // Procurar type
	        Query<Type> tipo = session.createQuery("from Type where type = :name", Type.class);
	        tipo.setParameter("name", typeName);

	        Type type;
	        List<Type> tipos = tipo.getResultList();
	        if (tipos.isEmpty()) {
	            type = new Type();
	            type.setType(typeName);
	            session.persist(type);
	        } else {
	            type = tipos.get(0);
	        }

	        // Associar a um programa
	        p.setPartner(partner);
	        p.setType(type);

	        partner.adicionarPrograma(p);
	        type.adicionarPrograma(p);

	        // persistir programa
	        session.persist(p);

	        tx.commit();
	        System.out.println("Programa criado com sucesso: " + p.getNomeP());

	    } catch (Exception e) {
	        if (tx != null) tx.rollback();
	        System.out.println("Erro ao criar programa: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	
	//Imprimir Programas
	public void imprimirProgramas() {
		
		try (Session session = sessionFactory.openSession()){
			session.beginTransaction();
			
			List <Program>programs = session.createQuery("from Program", Program.class).list();// Cria um query, procura e devolve os programas
			for(Program p: programs) { //ciclo para imprimir um programa de cada vez
				System.out.println(p);
			}
			session.getTransaction().commit();
		}
	}
	
	public void setLocalizacaoPrograma(String nomePrograma, String novaLocalizacao) {
	    Transaction tx = null; // Declarar transação
	    try (Session session = sessionFactory.openSession()) { 
	        tx = session.beginTransaction(); //Inicia transação

	        // Procurar o programa pelo nome	
	        Query<Program> query = session.createQuery("from Program where nomeP = :nome", Program.class);
	        query.setParameter("nome", nomePrograma);
	        List<Program> programas = query.getResultList();

	        if (!programas.isEmpty()) {
	            Program p = programas.get(0); // pega o primeiro resultado (caso haja duplicados)
	            p.setLocation(novaLocalizacao); // altera a localização

	            tx.commit();
	            System.out.println("Localização atualizada com sucesso para: " + novaLocalizacao);
	        } else {
	            System.out.println("Programa com o nome '" + nomePrograma + "' não encontrado.");
	            tx.rollback();
	        }

	    } catch (Exception e) {
	        if (tx != null) tx.rollback();
	        e.printStackTrace();
	        System.out.println("Erro ao atualizar a localização: " + e.getMessage());
	    }
	}
	
	//validar a palavra-passe
	public boolean validarPalavraPasse(String password) {
		
		if (password == null) { //se estiver vazio
			System.out.println("Tem que introduzir uma palavra-passe!");
			return false;
		}
		
		else if (password.length() < 8) { //se for menor que 8
			System.out.println("A palavra-passe tem de ter pelo menos 8 dígitos.");
			return false;
		}
		
		boolean numero = false; //criar variavel numero e começa como false
		for (char c: password.toCharArray()) { //para cada caracter na palavra (associa a um array)
			if (Character.isDigit(c)) { //verifica se tem caracter que é um número
				numero = true;
				break;
			}
		}
		
		if(!numero) { //se for diferente de ter número
			System.out.println("A palavra-passe tem de ter pelo menos um número");
			return false;
		}
		
		boolean letra = false;  //criar variavel letra e começa como false
		for (char c:password.toCharArray()) {  //para cada caracter na palavra (associa a um array)
			if (Character.isLetter(c)) {  //verifica se tem caracter que é um letra
				letra = true;
				break;
			}
		}
		
		if (!letra) {
			System.out.println("A palavra-passe tem de ter pelo menos uma letra");
			return false;
		}
		
		return true;
	}
	
	public boolean validarEmail(String email) {
		
		if (email == null) { //se estiver vazio 
			System.out.println("Tem que introduzir um e-mail!");
			return false;
		}
		
		boolean arroba = false; //arroba começa como falsa
		for (char a: email.toCharArray()) { //verifica carcater a caracter
			if (a == '@') { //se tiver um @ é true
				arroba = true;
				break;
			}
		}
		
		if(!arroba) { //se não tiver arroba dá erro
			System.out.println("A palavra-passe tem de ser do tipo 'xxx@xxx'");
			return false;
		}
		
		return true;
		
	}
            
}
