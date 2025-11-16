package equipa2;

/**
 * 
 */
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
/**
 * 
 */
public class Main {
	public static void main(String[]args) {
		ProgramManager manager = new ProgramManager();
		Scanner input = new  Scanner(System.in);
		manager.setup();

	
	
		boolean continuar= true;
		int opcao;
		while (continuar) {
			System.out.println("1- Criar conta como gestor");
			System.out.println("2- Criar conta como estudante");
			System.out.println("3- Fazer login");
			System.out.println("4- Sair");
			
			opcao= input.nextInt();
			input.nextLine();
			switch(opcao) {
			case 1 :
				System.out.println("Insira o nome");
				String nome= input.nextLine();
				System.out.println("Insira o email");
				String email= input.nextLine();
				System.out.println("Insira a password");
				String password= input.nextLine();
				User user= new User(nome,email,password);
				manager.adicionarUser(user);
				break;
				
			case 2: 
				System.out.println("Insira o nome: ");
				String nomeS= input.nextLine();
				
				System.out.println("Insira o email: ");
				String emailS= input.nextLine();
				
				System.out.println("Insira a password: ");
				String passwordS= input.nextLine();
				
				System.out.println("Insira o numero: ");
				String numeroSS = input.nextLine();
				int numeroS;
				try {
					numeroS = Integer.parseInt(numeroSS); //tenta converter string em numero inteiro
				}
				catch (NumberFormatException e){ //erro para quando tenta transformar algo que não é número em inteiro
					System.out.println("O número de estudante é composto apenas por números.");
					break;
				}
				
				Student student =new Student(nomeS,emailS,passwordS,numeroS);
				manager.adicionarStudent(student);
				break;
				
			case 3:
				System.out.println("Insira o email de utilizador");
				email=input.nextLine();
				
				System.out.println("Insira a password");
				String pass= input.nextLine();
				
				User u=manager.loginUtilizador(email, pass);
				
				if(u==null) {
					System.out.println("Credenciais erradas");
					System.out.println("1 - Redefinir palavra-passe");
					System.out.println("2 - Tentar novamente");
					
					opcao= input.nextInt();
					input.nextLine();
					switch(opcao) {
					case 1: 
						manager.recuperarPasse(email, input);
						
					case 2:
						continue;
						
					default: 
						System.out.println("Opção não existe");
					}
					
				}
				if (u instanceof User) {
				    System.out.println("Bem-vindo Admin");
				    boolean continuarA=true;
				    int opcaoA=0;
				    while(continuarA) {
					    System.out.println("1- Criar tipo de Programa");
					    System.out.println("2- Ver tipo de Programa");
					    System.out.println("3- Criar Programa");
					    System.out.println("4- Ver Programas");
					    System.out.println("5- Mudar localização de Programa");
					    System.out.println("6- Ver Utilizadores");
					    System.out.println("7- Ver Utilizadores Inscritos em Programas");
					    System.out.println("8- Sair");
					    opcaoA= input.nextInt();
						input.nextLine();
						switch(opcaoA) {
						
						case 1: 
							System.out.println("Insira um tipo de programa: ");
							String nomeTipo=input.nextLine();
							Type type= new Type(nomeTipo);
							manager.adicionarType(type);
							
							break;
							
						case 2:
							manager.printType();
							break;
							
						case 3:
							 System.out.println("Insira o nome do programa: ");
						     String nomeP = input.nextLine();

						     System.out.println("Insira uma descrição: ");
						     String description = input.nextLine();

						     System.out.println("Insira a localização do programa: ");
						     String location = input.nextLine();

						     System.out.println("Insira o contacto responsável: ");
						     int contact = input.nextInt();
						     input.nextLine();

						     System.out.println("Insira a que tipo o programa pertence: ");
						     String typeN= input.nextLine();
						        
						     System.out.println("Insira a que partner o programa pertence: ");
						     String partner= input.nextLine();

						     System.out.println("Insira as vagas que o programa tem: ");
						     int vagas = input.nextInt();

						        //chamar o método
						     Program p = new Program (nomeP, description, location, contact, vagas);
						     manager.criarPrograma( p, typeN, partner );
						     break;
						        
						case 4:
							manager.imprimirProgramas();
							break;
							
						case 5:
							System.out.println("Qual o nome do programa");
							String nomePrograma= input.nextLine();
							System.out.println("Insira a nova localização");
							String novaLocalizacao=input.nextLine();
							manager.setLocalizacaoPrograma(nomePrograma, novaLocalizacao);
							break;
							
						case 6:
							manager.printUsers();
							break;
							
						case 8:
							System.out.println("A Sair... Menu principal");
							continuarA=false;
							break;
							
						default:
							System.out.print("Opção não existe");
		
						}
				    }
				}

				else if (u instanceof Student) {
				    System.out.println("Bem-vindo Estudante");
				    boolean continuarE=true;
				    int opcaoE=0;
				    while(continuarE) {
					    System.out.println("1- Procurar Tipo");
					    System.out.println("2- Ver tipos de programa");
					    System.out.println("3- Mostrar Programas");
					    System.out.println("4- Procurar Programa");
					    System.out.println("5- Ver Programas");
					    System.out.println("6- Inscrever em Programa");
					    System.out.println("7- Sair");
					    opcaoE= input.nextInt();
						input.nextLine();
						switch(opcaoE) {
						
						case 1: 
							System.out.println("Insira o tipo que procura");
							String nomeTipo=input.nextLine();
							manager.pesquisarTipo(nomeTipo);
							break;
							
						case 2:
							manager.printType();
							break;
							
						case 3:
							manager.imprimirProgramas();
							break;
							
						case 4:
							
						case 5:
							manager.imprimirProgramas();
							break;
							
						case 6:
							
						}
					    
					    
				    }
				}
			break;
				
			case 4: 
				System.out.println("A sair...");
				continuar=false;
				break;
				
			default: 
				System.out.println("Opção não existe");
			}
			

		}

	}

	
}
	
