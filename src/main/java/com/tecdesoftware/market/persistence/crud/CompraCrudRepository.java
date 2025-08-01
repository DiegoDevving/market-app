package com.tecdesoftware.market.persistence.crud;

import aj.org.objectweb.asm.commons.Remapper;
import com.tecdesoftware.market.persistence.entity.Cliente;
import com.tecdesoftware.market.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompraCrudRepository extends CrudRepository<Compra, Integer> {
    Optional<List<Compra>> findByidCliente(String idCliente);
}
