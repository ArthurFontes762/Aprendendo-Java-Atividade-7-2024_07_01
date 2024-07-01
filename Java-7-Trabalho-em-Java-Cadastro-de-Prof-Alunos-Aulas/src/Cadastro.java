import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Cadastro {
    Scanner scanner = new Scanner(System.in);
    Alunos alunos = new Alunos();

    void addAlunos(){
        List<String[]> lista = new ArrayList<>();
        alunos.connect();
        alunos.createTableAlunos();
        while(true) {
            String[] nome ={"", "", ""};
            System.out.println("Para sair do loop, digite a palavra 'fim'");
            System.out.println("Aluno, digite seu nome:");
            nome[0] = scanner.nextLine();
            // Saída do loop
            if (nome[0].equalsIgnoreCase("fim")) {
                break;
            }
            System.out.println("Aluno, digite sua idade:");
            nome[1] = scanner.nextLine();
            System.out.println("Front End ou Back End:");
            nome[2] = scanner.nextLine();
            lista.add(nome);
        }
        alunos.insertAluno(lista);
        alunos.close();

    void mostrarAlunos(){
        alunos.connect();
        alunos.attAlunos();
        alunos.close();
    }

    /*void mostrarAulas(){
        alunos.connect();
        professores.connect();
        aulas.queryAulas();
        alunos.attAlunos();
        professores.attProfessores;
        alunos.close();
        professores.close();
    }*/

    void limparBancoDeDados(){
        alunos.connect();
        alunos.deleteAlunoProfessor();
        alunos.close();
    }

    void imprimirMenu(){
        System.out.println("Escolha uma opção:");
        System.out.println("1- Aluno: Cadastrar nome completo e idade");
        System.out.println("2- Professores: Separar aulas dos Alunos por idade");
        System.out.println("3- (EM MANUTENÇÃO) Aluno/Professores: Mostrar aulas");
        System.out.println("4- Remover Aluno ou Professores");
        System.out.println("5- Sair");
    }
}
