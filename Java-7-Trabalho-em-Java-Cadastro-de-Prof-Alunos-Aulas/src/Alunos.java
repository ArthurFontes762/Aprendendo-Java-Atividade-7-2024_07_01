import java.util.Scanner;
import java.sql.*;
import java.util.List;

public class Alunos {
    Scanner scanner = new Scanner(System.in);
    private static final String URL = "jdbc:sqlite:BancoUsers.db";
    private Connection connection;

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(URL);
            connection.setAutoCommit(false);
            System.out.println("Conexão realizada! Banco de dados: " + URL);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC do SQLite não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
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

    public void createTableAlunos() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS Alunos (ID INTEGER PRIMARY KEY, Aluno VARCHAR, Idade INTEGER, Aula VARCHAR)");
            connection.commit();
            System.out.println("Tabela criada ou já existe.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    public void insertAluno(List<String[]> lista) {
        try (PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO alunos(Nome, Idade, Aula) VALUES(?, ?, ?)")) {
            for (String[] s : lista) {
                insertStatement.setString(1, s[0]);
                insertStatement.setInt(2, Integer.parseInt(s[1]));
                insertStatement.setString(3, s[2]);
                insertStatement.executeUpdate();
            }
            connection.commit();
            System.out.println("Aluno inserido.");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir o aluno: " + e.getMessage());
        }
    }

    /* public void queryClients() {
        try (PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM Alunos WHERE Idade >= 18 ")) {
            ResultSet resultSet = selectStatement.executeQuery();
            System.out.println("Clientes:");
            while (resultSet.next()) {
                int idade = resultSet.getInt("Idade");
                String nome = resultSet.getString("Nome");
                System.out.println("Nome: " + nome + ", Idade: "+ idade);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar clientes: " + e.getMessage());
        }
    } */

    public void attAlunos() {
        try (PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM Alunos WHERE Idade < 18")) {
            ResultSet resultSet = selectStatement.executeQuery();
            System.out.println("Alunos menores ou iguais de 18 anos de Front End:");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nomeAluno = resultSet.getString("Nome");
                nomeAluno = nomeAluno.concat("(Front End)");
                updateAlunos(id, nomeAluno);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar alunos de Front End: " + e.getMessage());
        }
        try (PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM Alunos WHERE Idade >= 19")) {
            ResultSet resultSet = selectStatement.executeQuery();
            System.out.println("Alunos maiores ou iguais de 19 anos estão no Back End:");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nome = resultSet.getString("Nome");
                nome = nome.concat("(Back End)");
                updateAlunos(id,nome);
            }
        } catch (SQLException e) {
        System.out.println("Erro ao consultar alunos de Back End: " + e.getMessage());
        }
    }

    public void updateAlunos(int id, String novoNome) {
        try (PreparedStatement updateStatement = connection.prepareStatement("UPDATE Alunos SET Nome = ? WHERE ID = ?")) {
            updateStatement.setString(1, novoNome);
            updateStatement.setInt(2, id);
            updateStatement.executeUpdate();
            connection.commit();
            System.out.println("Alunos atualizado.");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar alunos: " + e.getMessage());
        }
    }

    public void deleteAlunoProfessor() {
        try (PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM Alunos WHERE Nome = ?")) {
            System.out.println("Insira o nome do Aluno/Professor a ser deletado:");
            deleteStatement.setString(1, scanner.nextLine());
            deleteStatement.executeUpdate();
            connection.commit();
            System.out.println("Aluno/Professores deletado.");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar Aluno/Professores: " + e.getMessage());
        }
    }
}
