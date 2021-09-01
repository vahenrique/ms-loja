package vahenrique.ms.loja.loja.domain.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import vahenrique.ms.loja.loja.domain.enumeration.StatusPedido;
import vahenrique.ms.loja.loja.domain.model.Pedido;
import vahenrique.ms.loja.loja.domain.repository.PedidoRepository;

@AllArgsConstructor
@Service
public class PedidoService {

	private PedidoRepository pedidoRepository;

	public Pedido novo() {
		Pedido pedido = new Pedido();
		pedido.setDtGeracao(OffsetDateTime.now());
		pedido.setStatus(StatusPedido.PENDENTE);
		pedido.setValorFrete(BigDecimal.ZERO);
		return pedido;
	}

	@Transactional
	public Pedido save(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	@Transactional
	public void delete(UUID id) {
		pedidoRepository.deleteById(id);
	}
}
