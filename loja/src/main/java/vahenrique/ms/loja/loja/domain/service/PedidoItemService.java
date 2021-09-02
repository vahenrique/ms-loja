package vahenrique.ms.loja.loja.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import vahenrique.ms.loja.loja.domain.model.PedidoItem;
import vahenrique.ms.loja.loja.domain.repository.PedidoItemRepository;

@AllArgsConstructor
@Service
public class PedidoItemService {

	private PedidoItemRepository pedidoItemRepository;

	@Transactional
	public PedidoItem save(PedidoItem pedidoItem) {
		return pedidoItemRepository.save(pedidoItem);
	}

	@Transactional
	public void delete(UUID id) {
		pedidoItemRepository.deleteById(id);
	}
}
