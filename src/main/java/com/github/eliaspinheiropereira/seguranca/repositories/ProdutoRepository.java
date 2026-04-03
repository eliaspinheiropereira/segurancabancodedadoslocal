package com.github.eliaspinheiropereira.seguranca.repositories;

import com.github.eliaspinheiropereira.seguranca.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
}
