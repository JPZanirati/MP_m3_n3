package cadastrobd.model;
import cadastro.model.util.ConectorBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author JPZanirati
 */

public class PessoaFisicaDAO {
    public static PessoaFisica getPessoa(int idPessoa) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConectorBD.getConnection();
            statement = ConectorBD.getPrepared("SELECT * FROM pessoa_Fisica WHERE idPessoa = ?");
            statement.setInt(1, idPessoa);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {

                String nome = resultSet.getString("nome");
                String cidade = resultSet.getString("cidade");
                String endereco = resultSet.getString("endereco");
                String uf = resultSet.getString("uf");
                int telefone = resultSet.getInt("telefone");
                String tipoPessoa = resultSet.getString("tipoPessoa");
                int idFisica = resultSet.getInt("idFisica");
                long cpf = resultSet.getLong("cpf");
                Date dt_nasc = resultSet.getDate("dt_nasc");

                return new PessoaFisica(idPessoa, nome, cidade, endereco, uf, telefone, tipoPessoa, idFisica, cpf, dt_nasc);
            }

            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            ConectorBD.close(resultSet);
            ConectorBD.close(statement);
            ConectorBD.close(connection);
        }
    }

    public List<PessoaFisica> getPessoas() {
        List<PessoaFisica> pessoas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConectorBD.getConnection();
            statement = ConectorBD.getPrepared("SELECT * FROM pessoa_Fisica");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                
                int idPessoa = resultSet.getInt("idPessoa");
                String nome = resultSet.getString("nome");
                String cidade = resultSet.getString("cidade");
                String endereco = resultSet.getString("endereco");
                String uf = resultSet.getString("uf");
                int telefone = resultSet.getInt("telefone");
                String tipoPessoa = resultSet.getString("tipoPessoa");
                int idFisica = resultSet.getInt("idFisica");
                long cpf = resultSet.getLong("cpf");
                Date dt_nasc = resultSet.getDate("dt_nasc");

                PessoaFisica pessoa = new PessoaFisica(idPessoa, nome, cidade, endereco, uf, telefone, tipoPessoa, idFisica, cpf, dt_nasc);
                pessoas.add(pessoa);
            }

            return pessoas;
        } catch (SQLException e) {
            return pessoas;
        } finally {
            ConectorBD.close(resultSet);
            ConectorBD.close(statement);
            ConectorBD.close(connection);
        }
    }

    public void incluir(PessoaFisica pessoa) {
        Connection connection = null;
        PreparedStatement pessoaStatement = null;
        PreparedStatement fisicaStatement = null;

        try {
            connection = ConectorBD.getConnection();
            connection.setAutoCommit(false);
            
            pessoaStatement = ConectorBD.getPrepared("INSERT INTO pessoa (nome, cidade, endereco, uf, telefone, tipoPessoa) VALUES (?, ?, ?, ?, ?, ?)");
            pessoaStatement.setString(1, pessoa.getNome());
            pessoaStatement.setString(2, pessoa.getCidade());
            pessoaStatement.setString(3, pessoa.getEndereco());
            pessoaStatement.setString(4, pessoa.getUf());
            pessoaStatement.setInt(5, pessoa.getTelefone());
            pessoaStatement.setString(6, pessoa.getTipoPessoa());
            pessoaStatement.executeUpdate();

            ResultSet generatedKeys = pessoaStatement.getGeneratedKeys();
            int idFisica = 0;
            if (generatedKeys.next()) {
                idFisica = generatedKeys.getInt(1);
            }

            fisicaStatement = connection.prepareStatement("INSERT INTO pessoa_Fisica (cpf, dt_Nasc, idPessoa) VALUES (?, ?, ?)");
            fisicaStatement.setLong(1, pessoa.getCpf());
            fisicaStatement.setDate(2, pessoa.getDtNasc());
            fisicaStatement.setInt(3, idFisica);
            fisicaStatement.executeUpdate();

            connection.commit();
            System.out.println("Adicionado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar!" +e);
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException rollbackEx) {
            }
        } finally {
            ConectorBD.close(pessoaStatement);
            ConectorBD.close(fisicaStatement);
            ConectorBD.close(connection);
        }
    }

    public void alterar(PessoaFisica pessoa) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConectorBD.getConnection();
            connection.setAutoCommit(false);
            
            statement = ConectorBD.getPrepared("UPDATE pessoa SET nome=?, cidade=?, endereco=?, uf=?, telefone=?, tipoPessoa=? WHERE idPessoa=?");
            statement.setString(1, pessoa.getNome());
            statement.setString(2, pessoa.getCidade());
            statement.setString(3, pessoa.getEndereco());
            statement.setString(4, pessoa.getUf());
            statement.setInt(5, pessoa.getTelefone());
            statement.setString(6, pessoa.getTipoPessoa());
            statement.setInt(7, pessoa.getIdPessoa());
            statement.executeUpdate();

            statement = ConectorBD.getPrepared("UPDATE pessoa_Fisica SET cpf=?, dt_nasc=? WHERE idFisica=?");
            statement.setLong(1, pessoa.getCpf());
            statement.setDate(2, pessoa.getDtNasc());
            statement.setInt(3, pessoa.getIdFisica());
            statement.executeUpdate();

            connection.commit();
            System.out.println("Atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar!" +e);
            try {
                if (connection != null) {
                    connection.rollback(); 
                }
            } catch (SQLException rollbackEx) {
            }
        } finally {
            ConectorBD.close(statement);
            ConectorBD.close(connection);
        }
    }

    public void excluir(PessoaFisica pessoa) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConectorBD.getConnection();
            connection.setAutoCommit(false); 

            statement = ConectorBD.getPrepared("DELETE FROM pessoa_Fisica WHERE idFisica=?");
            statement.setInt(1, pessoa.getIdFisica());
            statement.executeUpdate();

            statement = ConectorBD.getPrepared("DELETE FROM pessoa WHERE idPessoa=?");
            statement.setInt(1, pessoa.getIdPessoa());
            statement.executeUpdate();

            connection.commit(); 
            System.out.println("Removido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao Remover!" +e);
            try {
                if (connection != null) {
                    connection.rollback(); 
                }
            } catch (SQLException rollbackEx) {
            }
        } finally {
            ConectorBD.close(statement);
            ConectorBD.close(connection);
        }
    }
}
