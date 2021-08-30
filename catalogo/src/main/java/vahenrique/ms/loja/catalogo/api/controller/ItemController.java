package vahenrique.ms.loja.catalogo.api.controller;

import java.util.List;
import java.util.UUID;

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
import vahenrique.ms.loja.catalogo.domain.model.Item;
import vahenrique.ms.loja.catalogo.domain.repository.ItemRepository;
import vahenrique.ms.loja.catalogo.domain.service.ItemService;

@AllArgsConstructor
@RestController
@RequestMapping("/items")
public class ItemController {

	private ItemRepository itemRepository;
	private ItemService itemService;

	@GetMapping
	public List<Item> listar() {
		return itemRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Item incluir(@RequestBody Item item) {
		return itemService.save(item);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Item> atualizar(@PathVariable UUID id, @RequestBody Item item) {
		if (!itemRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		item.setId(id);
		item = itemService.save(item);

		return ResponseEntity.ok(item);
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
