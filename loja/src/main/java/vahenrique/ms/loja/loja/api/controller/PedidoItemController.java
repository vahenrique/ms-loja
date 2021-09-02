package vahenrique.ms.loja.loja.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import vahenrique.ms.loja.loja.api.mapper.PedidoItemMapper;
import vahenrique.ms.loja.loja.api.model.PedidoItemDto;
import vahenrique.ms.loja.loja.api.model.PedidoItemInputDto;
import vahenrique.ms.loja.loja.api.model.PedidoItemQuantidadeInputDto;
import vahenrique.ms.loja.loja.domain.model.PedidoItem;
import vahenrique.ms.loja.loja.domain.repository.PedidoItemRepository;
import vahenrique.ms.loja.loja.domain.service.PedidoItemService;

@AllArgsConstructor
@RestController
@RequestMapping("itens")
public class PedidoItemController {

	private PedidoItemRepository pedidoItemRepository;
	private PedidoItemService pedidoItemService;
	private PedidoItemMapper pedidoItemMapper;

	@GetMapping("/{pedidoId}")
	public List<PedidoItemDto> listar(@PathVariable UUID pedidoId) {
		return pedidoItemMapper.toCollectionDto(pedidoItemRepository.findByPedidoId(pedidoId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoItemDto incluir(@Valid @RequestBody PedidoItemInputDto pedidoItemInputDto) {
		PedidoItem pedidoItem = pedidoItemService.save(pedidoItemMapper.toEntity(pedidoItemInputDto));
		return pedidoItemMapper.toDto(pedidoItem);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PedidoItemDto> alterarQuantidade(@PathVariable UUID id,
			@Valid @RequestBody PedidoItemQuantidadeInputDto pedidoItemQuantidadeInputDto) {
		Optional<PedidoItem> optionalPedidoItem = pedidoItemRepository.findById(id);

		if (!optionalPedidoItem.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		PedidoItem pedidoItem = optionalPedidoItem.get();
		pedidoItem.setQuantidade(pedidoItemQuantidadeInputDto.getQuantidade());
		pedidoItem = pedidoItemService.save(pedidoItem);

		return ResponseEntity.ok(pedidoItemMapper.toDto(pedidoItem));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable UUID id) {
		if (!pedidoItemRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		pedidoItemService.delete(id);

		return ResponseEntity.noContent().build();
	}
}
