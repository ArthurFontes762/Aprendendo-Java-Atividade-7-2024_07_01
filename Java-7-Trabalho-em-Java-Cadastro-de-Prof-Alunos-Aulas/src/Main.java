import java.sql.Connection;

public class Main {
    private Connection connection;

    public static void main(String[] args) {
        Cadastro cadastro = new Cadastro();
        boolean executar = true;
        while (executar) {
            cadastro.imprimirMenu();
            String opcao = cadastro.scanner.nextLine();
            switch (opcao) {
                case "1":
                    cadastro.addAlunos();
                    break;

                case "2":
                    cadastro.addProfessores();
                    break;

                case "3":
                    cadastro.mostrarAlunos();
                    break;

                case "4":
                    cadastro.limparBancoDeDados();
                    break;

                case "5":
                    cadastro.scanner.close();
                    executar = false;
                    break;

                default:
                    System.out.println("Opção inválida, tente novamente.");
                    break;
            }
        }
    }
}