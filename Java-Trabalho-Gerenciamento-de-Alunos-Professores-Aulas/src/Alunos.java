import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Alunos {

    public void createTable() {
        try (Statement statement = BancoDeDados.connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS Alunos (ID INTEGER PRIMARY KEY, Nome VARCHAR, Idade INTEGER, Aula VARCHAR)");
            BancoDeDados.connection.commit();
            System.out.println("Tabela criada ou já existe.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    public void inserirAlunos(List<String[]> lista) {
        try (PreparedStatement insertStatement = BancoDeDados.connection.prepareStatement("INSERT INTO Alunos(Nome, Idade, Aula) VALUES(?, ?, ?)")) {
            for (String[] s : lista) {
                insertStatement.setString(1, s[0]);
                insertStatement.setInt(2, Integer.parseInt(s[1]));
                insertStatement.setString(3, s[2]);
                insertStatement.executeUpdate();
            }
            BancoDeDados.connection.commit();
            System.out.println("Alunos inseridos.");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir aluno: " + e.getMessage());
        }
    }

    public void lerAluno(String nomeQuery) {
        try (PreparedStatement selectStatement = BancoDeDados.connection.prepareStatement("SELECT * FROM Alunos WHERE Nome = ? ")) {
            selectStatement.setString(1, nomeQuery);
            ResultSet resultSet = selectStatement.executeQuery();
            System.out.println("Alunos");
            int idade = resultSet.getInt("Idade");
            String nome = resultSet.getString("Nome");
            String aula = resultSet.getString("Aula");
            System.out.println("Nome: " + nome + ", Idade: "+ idade+ ", Aula: "+ aula);
        } catch (SQLException e) {
            System.out.println("Erro ao consultar alunos: " + e.getMessage());
        }
    }

    public void deleteAlunos(String nome) {
        try (PreparedStatement deleteStatement = BancoDeDados.connection.prepareStatement("DELETE FROM Alunos WHERE Nome = ?")) {
            deleteStatement.setString(1, nome);
            deleteStatement.executeUpdate();
            BancoDeDados.connection.commit();
            System.out.println("Aluno deletado.");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar alunos: " + e.getMessage());
        }
    }
}
