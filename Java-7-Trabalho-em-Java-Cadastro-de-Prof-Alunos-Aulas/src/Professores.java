import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Professores {
    Scanner scanner = new Scanner(System.in);
    Professores professores = new Professores();
    Alunos alunos = new Alunos();
    private Connection connection;

    public void createTableProfessores() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS Professores (ID INTEGER PRIMARY KEY, Professor VARCHAR, Idade INTEGER, Aula VARCHAR)");
            connection.commit();
            System.out.println("Tabela criada ou já existe.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    void addProfessores(){
        List<String[]> listaprof = new ArrayList<>();
        alunos.connect();
        professores.createTableProfessores();
        while (true) {
            String[] nomeprof ={"", "", ""};
            System.out.println("Para sair do loop, digite a palavra 'fim'");
            System.out.println("Professor, digite seu nome:");
            nomeprof[0] = scanner.nextLine();
            // Saída do loop
            if (nomeprof[0].equalsIgnoreCase("fim")) {
                break;
            }
            System.out.println("Professor, digite sua idade:");
            nomeprof[1] = scanner.nextLine();
            System.out.println("Front End ou Back End:");
            nomeprof[2] =scanner.nextLine();
            listaprof.add(nomeprof);
        }
        professores.insertProfessor(listaprof);
        professores.close();
    }

    public void insertProfessor(List<String[]> listaprof) {
        try (PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO professores(Nome, Idade, Aula) VALUES(?, ?, ?)")) {
            for (String[] s : listaprof) {
                insertStatement.setString(1, s[0]);
                insertStatement.setInt(2, Integer.parseInt(s[1]));
                insertStatement.setString(3, s[2]);
                insertStatement.executeUpdate();
            }
            connection.commit();
            System.out.println("Professor inserido.");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir o professor: " + e.getMessage());
        }
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexão fechada.");
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}
