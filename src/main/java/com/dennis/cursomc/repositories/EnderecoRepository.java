package com.dennis.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dennis.cursomc.domain.Endereco;
import com.dennis.cursomc.domain.Produto;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
