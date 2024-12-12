package com.artsaboaria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artsaboaria.entidade.Estoque_Ent;

public interface EstoqueRepositorio extends JpaRepository<Estoque_Ent, Long>{

}
