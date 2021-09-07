package vahenrique.ms.loja.catalogo.api.controller;

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
import vahenrique.ms.loja.catalogo.api.mapper.ItemMapper;
import vahenrique.ms.loja.catalogo.api.model.ItemDto;
import vahenrique.ms.loja.catalogo.api.model.ItemInputDto;
import vahenrique.ms.loja.catalogo.domain.model.Item;
import vahenrique.ms.loja.catalogo.domain.repository.ItemRepository;
import vahenrique.ms.loja.catalogo.domain.service.ItemService;

@AllArgsConstructor
@RestController
@RequestMapping("/items")
public class ItemController {

	private ItemRepository itemRepository;
	private ItemService itemService;
	private ItemMapper itemMapper;

	@GetMapping
	public List<ItemDto> listar() {
		return itemMapper.toCollectionDto(itemRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ItemDto> visualizar(@PathVariable UUID id) {
		Optional<Item> optionalItem = itemRepository.findById(id);

		if (!optionalItem.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(itemMapper.toDto(optionalItem.get()));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ItemDto incluir(@Valid @RequestBody ItemInputDto itemInputDto) {
		Item item = itemService.save(itemMapper.toEntity(itemInputDto));
		return itemMapper.toDto(item);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ItemDto> atualizar(@PathVariable UUID id, @Valid @RequestBody ItemInputDto itemInputDto) {
		if (!itemRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		Item item = itemMapper.toEntity(itemInputDto);
		item.setId(id);
		item = itemService.save(item);

		return ResponseEntity.ok(itemMapper.toDto(item));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable UUID id) {
		if (!itemRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		itemService.delete(id);

		return ResponseEntity.noContent().build();
	}
}
