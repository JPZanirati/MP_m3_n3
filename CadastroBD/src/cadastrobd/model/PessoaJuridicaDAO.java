package cadastrobd.model;
import cadastro.model.util.ConectorBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JPZanirati
 */

public class PessoaJuridicaDAO {
    public static PessoaJuridica getPessoa(int idPessoa) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConectorBD.getConnection();
            statement = ConectorBD.getPrepared("SELECT * FROM pessoa_Juridica WHERE idJuridica = ?");
            statement.setInt(1, idPessoa);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                
                String nome = resultSet.getString("nome");
                String cidade = resultSet.getString("cidade");
                String endereco = resultSet.getString("endereco");
                String uf = resultSet.getString("uf");
                int telefone = resultSet.getInt("telefone");
                String tipoPessoa = resultSet.getString("tipoPessoa");
                int idJuridica = resultSet.getInt("idJuridica");
                long cnpj = resultSet.getLong("cnpj");
                String razaoSocial = resultSet.getString("razaoSocial");

                return new PessoaJuridica(idPessoa, nome, cidade, endereco, uf, telefone, tipoPessoa, idJuridica, cnpj, razaoSocial);
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

    public List<PessoaJuridica> getPessoas() {
        List<PessoaJuridica> pessoas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConectorBD.getConnection();
            statement = ConectorBD.getPrepared("SELECT * FROM pessoa_Juridica");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                
                int idPessoa = resultSet.getInt("idPessoa");
                String nome = resultSet.getString("nome");
                String cidade = resultSet.getString("cidade");
                String endereco = resultSet.getString("endereco");
                String uf = resultSet.getString("uf");
                int telefone = resultSet.getInt("telefone");
                String tipoPessoa = resultSet.getString("tipoPessoa");
                int idJuridica = resultSet.getInt("idJuridica");
                long cnpj = resultSet.getLong("cnpj");
                String razaoSocial = resultSet.getString("razaoSocial");

                PessoaJuridica pessoa = new PessoaJuridica(idPessoa, nome, cidade, endereco, uf, telefone, tipoPessoa, idJuridica, cnpj, razaoSocial);
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

    public void incluir(PessoaJuridica pessoa) {
        Connection connection = null;
        PreparedStatement pessoaStatement = null;
        PreparedStatement juridicaStatement = null;

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
            int idJuridica = -1;
            if (generatedKeys.next()) {
                idJuridica = generatedKeys.getInt(1);
            }

            juridicaStatement = ConectorBD.getPrepared("INSERT INTO pessoa_Juridica (cnpj, razaoSocial, idJuridica) VALUES (?, ?, ?)");
            juridicaStatement.setLong(2, pessoa.getCnpj());
            juridicaStatement.setString(3, pessoa.getRazaoSocial());
            juridicaStatement.setInt(1, idJuridica);
            juridicaStatement.executeUpdate();

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
            ConectorBD.close(juridicaStatement);
            ConectorBD.close(connection);
        }
    }

    public void alterar(PessoaJuridica pessoa) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConectorBD.getConnection();
            connection.setAutoCommit(false); 

            statement = ConectorBD.getPrepared("UPDATE pessoa SET nome=?, cidade=?, endereco=?, uf=?, telefone=?, tipoPessoa=? WHERE id=?");
            statement.setString(1, pessoa.getNome());
            statement.setString(2, pessoa.getCidade());
            statement.setString(3, pessoa.getEndereco());
            statement.setString(4, pessoa.getUf());
            statement.setInt(5, pessoa.getTelefone());
            statement.setString(6, pessoa.getTipoPessoa());
            statement.setInt(7, pessoa.getIdPessoa());
            statement.executeUpdate();

            statement = ConectorBD.getPrepared("UPDATE pessoa_Juridica SET cnpj=?, razaoSocial=? WHERE id=?");
            statement.setLong(1, pessoa.getCnpj());
            statement.setString(6, pessoa.getRazaoSocial());
            statement.setInt(2, pessoa.getIdJuridica());
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

 public void excluir(PessoaJuridica pessoa) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConectorBD.getConnection();
            connection.setAutoCommit(false); 

            statement = ConectorBD.getPrepared("DELETE FROM pessoa_Juridica WHERE idJuridica=?");
            statement.setInt(1, pessoa.getIdJuridica());
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
