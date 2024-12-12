package com.exemplo.importacao.controller;


import com.exemplo.importacao.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class TableController {

    @Autowired
    private com.exemplo.importacao.service.TableService tableService;

    @PostMapping("/create-table")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Obtemos o nome do arquivo sem espaços e sem extensão
            String fileName = file.getOriginalFilename().replaceAll("\\s", "").replaceAll("\\..*", "");

            // Processa o arquivo com base no tipo (CSV ou Excel)
            tableService.processFile(file);

            // Aqui você pode fazer algo para criar a tabela no banco (se necessário)
            // tableService.createTable(fileName, tableData); // Descomente se estiver criando a tabela no banco

            // Retorna uma mensagem de sucesso
            return "Tabela '" + fileName + "' criada com sucesso!";
        } catch (Exception e) {
            // Caso algo dê errado, retorna uma mensagem de erro
            return "Erro ao processar o arquivo: " + e.getMessage();
        }
    }
}
