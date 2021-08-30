package vahenrique.ms.loja.catalogo.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import vahenrique.ms.loja.catalogo.domain.model.Item;
import vahenrique.ms.loja.catalogo.domain.repository.ItemRepository;

@AllArgsConstructor
@Service
public class ItemService {

	private ItemRepository itemRepository;

	@Transactional
	public Item save(Item item) {
		return itemRepository.save(item);
	}

	@Transactional
	public void delete(UUID id) {
		itemRepository.deleteById(id);
	}
}
