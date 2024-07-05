import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Cadastro {
    Scanner scanner = new Scanner(System.in);
    BancoDeDados bancoDeDados = new BancoDeDados();
    Alunos alunos = new Alunos();
    Professor professor = new Professor();
    Aula aula = new Aula();


    void adicionarAlunos(){
        List<String[]> lista = new ArrayList<>();
        bancoDeDados.connect();
        alunos.createTable();
        while(true) {
            String[] aluno ={"", "", ""};
            System.out.println("Digite o nome do aluno (ou fim para sair): ");
            aluno[0] = scanner.nextLine();
            // Saída do loop
            if (aluno[0].equalsIgnoreCase("fim")) {
                break;
            }
            System.out.println("Digite a idade do aluno");
            aluno[1] = scanner.nextLine();
            System.out.println("Digite a aula frequentada");
            aluno[2] = scanner.nextLine();
            lista.add(aluno);
        }
        alunos.inserirAlunos(lista);
        bancoDeDados.close();
    }

    void lerAluno(){
        bancoDeDados.connect();
        System.out.println("Digite o nome do aluno a ser consultado: ");
        alunos.lerAluno(scanner.nextLine());
        bancoDeDados.close();
    }
    void deletarAluno(){
        bancoDeDados.connect();
        System.out.println("Digite o nome do aluno a ser deletado: ");
        alunos.deleteAlunos(scanner.nextLine());
        bancoDeDados.close();
    }

    void adicionarProfessor(){
        List<String[]> lista = new ArrayList<>();
        bancoDeDados.connect();
        professor.createTable();
        while(true) {
            String[] professor ={"", "", ""};
            System.out.println("Digite o nome do professor (ou fim para sair): ");
            professor[0] = scanner.nextLine();
            //saída do loop
            if (professor[0].equalsIgnoreCase("fim")) {
                break;
            }
            System.out.println("Digite a formação do professor:");
            professor[1] = scanner.nextLine();
            System.out.println("Digite a aula frequentada:");
            professor[2] = scanner.nextLine();
            lista.add(professor);
        }
        professor.inserirProfessor(lista);
        bancoDeDados.close();
    }

    void lerProfessor(){
        bancoDeDados.connect();
        System.out.println("Digite o nome do professor a ser consultado: ");
        professor.lerProfessor(scanner.nextLine());
        bancoDeDados.close();
    }
    void deletarProfessor(){
        bancoDeDados.connect();
        System.out.println("Digite o nome do professor a ser deletado: ");
        professor.deleteProfessor(scanner.nextLine());
        bancoDeDados.close();
    }

    void consultarAula(){
        bancoDeDados.connect();
        System.out.println("Digite o nome da aula a ser consultada: ");
        aula.lerAula(scanner.nextLine());
        bancoDeDados.close();
    }

    void imprimirMenu(){
        System.out.println("Escolha uma opção:");
        System.out.println("1- Inserir alunos");
        System.out.println("2- Deletar aluno");
        System.out.println("3- Consultar aluno");
        System.out.println("4- Inserir professor");
        System.out.println("5- Consultar professor");
        System.out.println("6- Deletar professor");
        System.out.println("7- Consultar aula");
        System.out.println("8- Sair");
    }
}
