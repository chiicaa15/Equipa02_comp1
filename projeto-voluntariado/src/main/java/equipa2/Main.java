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
			System.out.println("3- Criar um tipo de programa");
			System.out.println("4- Imprimir tipos");
			System.out.println("5- Procurar um tipo de programa");
			System.out.println("6- Imprimir todos os utilizadores");
			System.out.println("7- Criar programa");
			System.out.println("8- Imprimir todos os programas existentes");
			System.out.println("9- Mudar localização do programa");
			System.out.println("10- Sair");
			
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
				int numeroS= input.nextInt();
				Student student =new Student(nomeS,emailS,passwordS,numeroS);
				manager.adicionarStudent(student);
				break;
				
			case 3:
				System.out.println("Insira um tipo de progranma: ");
				String nomeTipo=input.nextLine();
				Type type= new Type(nomeTipo);
				manager.adicionarType(type);
				
				break;
				
			case 4:
				manager.printType();
				
				break;
			case 5: 
				System.out.println("Insira o tipo que procura");
				nomeTipo=input.nextLine();
				manager.pesquisarTipo(nomeTipo);
				break;
				
			case 6:
				manager.printUsers();
				break;
          
			case 7:
          
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
			
			case 8:
				manager.imprimirProgramas();
				break;
				
			case 9:
				System.out.println("Qual o nome do programa");
				String nomePrograma= input.nextLine();
				System.out.println("Insira a nova localização");
				String novaLocalizacao=input.nextLine();
				manager.setLocalizacaoPrograma(nomePrograma, novaLocalizacao);
				break;
				
			case 10: 
				System.out.println("A sair...");
				continuar=false;
				break;
				
			default: 
				System.out.println("Opção não existe");
			}
			

		}

	}

	
}
	
