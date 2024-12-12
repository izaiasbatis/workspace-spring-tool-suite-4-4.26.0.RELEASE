package com.artsaboaria.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artsaboaria.entidade.Categoria_Ent;
import com.artsaboaria.repositorio.CategoriaRepositorio;


@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*")

public class CategoriaControle {

	@Autowired
	CategoriaRepositorio repositorio;
	
	@GetMapping("/listar")
	public List<Categoria_Ent> listar(){
		return repositorio.findAll();
	}
	
	@PostMapping("/salvar")
	public Categoria_Ent salvar (@RequestBody Categoria_Ent entity) {
		return repositorio.save(entity);
	}
	@DeleteMapping("/excluir/{id}")
		public ResponseEntity<String> excluir(@PathVariable Long id){
		if (!repositorio.existsById(id)) {
			return ResponseEntity.status(404).body("Categoria não identificado!");
		}
		repositorio.deleteById(id);
		return ResponseEntity.status(200).body("Excluído com sucesse!");
	}
}
