package com.importar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;
import java.util.Map;

@Service
public class BaseService {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public boolean checkAndCreateDatabase() {
        String databaseUrl = url.split("\\?")[0]; // Remove os parâmetros da URL para verificar a base de dados.
        String databaseName = databaseUrl.substring(databaseUrl.lastIndexOf("/") + 1);

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Verifica se o banco de dados existe
            try (Connection checkConnection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/", username, password)) {

                var statement = checkConnection.createStatement();
                var result = statement.executeQuery("SHOW DATABASES LIKE '" + databaseName + "'");
                if (!result.next()) {
                    // Se o banco não existir, cria o banco de dados
                    statement.executeUpdate("CREATE DATABASE " + databaseName);
                    System.out.println("Banco de dados criado: " + databaseName);
                }
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao verificar ou criar o banco de dados: " + e.getMessage());
            return false;
        }
    }

    public boolean createTableFromFile(String tableName, List<List<String>> tableData) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Verifica se a tabela já existe
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, tableName, new String[]{"TABLE"});
            if (resultSet.next()) {
                System.out.println("Tabela já existe: " + tableName);
                return false;
            }

            // Cria a tabela dinamicamente com base nas colunas do arquivo
            StringBuilder createTableSQL = new StringBuilder("CREATE TABLE " + tableName + " (");
            
            // Usa a primeira linha do arquivo para criar as colunas da tabela
            List<String> headers = tableData.get(0);
            for (int i = 0; i < headers.size(); i++) {
                String columnName = headers.get(i);
                createTableSQL.append(columnName).append(" VARCHAR(255)");
                if (i < headers.size() - 1) {
                    createTableSQL.append(", ");
                }
            }
            createTableSQL.append(")");

            // Executa a criação da tabela
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(createTableSQL.toString());
                System.out.println("Tabela criada: " + tableName);
            }

            // Insere os dados na tabela
            insertDataIntoTable(connection, tableName, tableData);
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao criar a tabela ou inserir os dados: " + e.getMessage());
            return false;
        }
    }

    private void insertDataIntoTable(Connection connection, String tableName, List<List<String>> tableData) throws SQLException {
        StringBuilder insertSQL = new StringBuilder("INSERT INTO " + tableName + " VALUES ");
        
        // Para cada linha de dados, cria a SQL de inserção
        for (int i = 1; i < tableData.size(); i++) { // Começa da segunda linha (dados)
            insertSQL.append("(");
            List<String> rowData = tableData.get(i);
            for (int j = 0; j < rowData.size(); j++) {
                insertSQL.append("'").append(rowData.get(j).replace("'", "''")).append("'");
                if (j < rowData.size() - 1) {
                    insertSQL.append(", ");
                }
            }
            insertSQL.append(")");
            if (i < tableData.size() - 1) {
                insertSQL.append(", ");
            }
        }

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(insertSQL.toString());
            System.out.println("Dados inseridos na tabela: " + tableName);
        }
    }
}
