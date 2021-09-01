package vahenrique.ms.loja.loja.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vahenrique.ms.loja.loja.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

}
