package vahenrique.ms.loja.loja.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vahenrique.ms.loja.loja.domain.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

}
