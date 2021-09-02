package vahenrique.ms.loja.loja.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vahenrique.ms.loja.loja.domain.model.PedidoItem;

@Repository
public interface PedidoItemRepository extends JpaRepository<PedidoItem, UUID> {

	public List<PedidoItem> findByPedidoId(UUID pedidoId);
}
