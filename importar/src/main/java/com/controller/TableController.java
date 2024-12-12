package com.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

@Service
public class TableController {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    // Método simplificado para criar tabela com o nome do arquivo
    public boolean createTableFromFile(String fileName, List<List<String>> tableData) {
        // Remove a extensão do arquivo e espaços
        String tableName = fileName.replaceAll("\\s", "").replaceAll("\\.\\w+$", "");

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
                createTableSQL.append(columnName).append(" VARCHAR(255)"); // Aqui você pode alterar para tipos mais específicos se necessário
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
                insertSQL.append("'").append(rowData.get(j).replace("'", "''")).append("'"); // Escapa as aspas simples
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
