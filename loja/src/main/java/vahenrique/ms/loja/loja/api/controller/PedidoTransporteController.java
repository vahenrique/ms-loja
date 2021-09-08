package vahenrique.ms.loja.loja.api.controller;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import vahenrique.ms.loja.loja.api.mapper.PedidoMapper;
import vahenrique.ms.loja.loja.api.model.PedidoDto;
import vahenrique.ms.loja.loja.api.model.PedidoTransporteDto;
import vahenrique.ms.loja.loja.domain.model.Pedido;
import vahenrique.ms.loja.loja.domain.repository.PedidoRepository;

@AllArgsConstructor
@RestController
@RequestMapping("/pedidotransporte")
public class PedidoTransporteController {

	private PedidoRepository pedidoRepository;
	private PedidoMapper pedidoMapper;

	@PutMapping("/{id}")
	public ResponseEntity<PedidoDto> atualizar(@PathVariable UUID id,
			@Valid @RequestBody PedidoTransporteDto pedidoTransporteDto) {
		Optional<Pedido> optionalPedido = pedidoRepository.findById(id);

		if (!optionalPedido.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Pedido pedido = optionalPedido.get();
		pedido.setStatus(pedidoTransporteDto.getStatus());
		pedido = pedidoRepository.save(pedido);
		return ResponseEntity.ok(pedidoMapper.toDto(pedido));
	}
}
