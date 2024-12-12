package com.exemplo.importacao.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TableService {

    public List<List<String>> processFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename().toLowerCase();
       if (fileName.endsWith(".xlsx") || fileName.endsWith(".xls")) {
            return processExcel(file);
        } else {
            throw new IOException("Formato de arquivo não suportado");
        }
    }

    // Processa o arquivo CSV


    // Processa o arquivo Excel (XLSX ou XLS)
    private List<List<String>> processExcel(MultipartFile file) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0); // Pega a primeira planilha
        List<List<String>> data = new ArrayList<>();
        
        for (Row row : sheet) {
            List<String> rowData = new ArrayList<>();
            for (Cell cell : row) {
                rowData.add(cell.toString());
            }
            data.add(rowData);
        }
        return data;
    }

    // Cria a tabela no banco de dados (simulando a criação no código)
    public void createTable(String tableName, List<List<String>> tableData) {
        // Lógica para criar a tabela no banco de dados
        // Aqui você pode criar uma tabela dinamicamente, criando as colunas conforme as colunas do arquivo.
        System.out.println("Criando tabela: " + tableName);
        for (List<String> row : tableData) {
            System.out.println(row);
        }
    }
}