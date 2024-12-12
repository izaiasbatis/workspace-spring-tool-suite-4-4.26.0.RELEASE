package com.artsaboaria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artsaboaria.entidade.Cliente_Ent;

public interface ClienteRepositorio extends JpaRepository<Cliente_Ent, Long>{

}
