/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastro.model.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author JPZanirati
 */

public class ConectorBD {
    private static final String DRIVE = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://localhost\\DESKTOP-GBQ1OK7\\SQLEXPRESS01:1433;databaseName=LOJA;encrypt=true;trustServerCertificate=true;";
    private static final String USUARIO = "loja";
    private static final String SENHA = "loja";
    
    public static Connection getConnection() {
        try {
            Class.forName(DRIVE);
            return DriverManager.getConnection(URL, USUARIO, SENHA);
            
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados." +e);
        }
    }

    public static PreparedStatement getPrepared(String sql) {
        try {
            Connection connection = getConnection();
            return connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar PreparedStatement." +e);
        }
    }

    public static ResultSet getSelect(String sql) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao executar consulta SQL." +e);
        }
    }

    public static void close(Connection connection) {
        try {
            if (connection != null)
                connection.close();             
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao fechar conexão." +e);
        }
    }
    
    
    public static void close(Statement statement) {
      try {  
          if (statement != null) 
                statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao fechar statement." +e);
        } 
    }

    public static void close(ResultSet resultSet) {
        try {
            if (resultSet != null)            
                resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao fechar resultSet." +e);
        }
    }
}

