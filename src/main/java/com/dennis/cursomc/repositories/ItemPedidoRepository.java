package com.dennis.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dennis.cursomc.domain.ItemPedido;
import com.dennis.cursomc.domain.Produto;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
