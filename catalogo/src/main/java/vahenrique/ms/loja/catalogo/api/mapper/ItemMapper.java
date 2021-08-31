package vahenrique.ms.loja.catalogo.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import vahenrique.ms.loja.catalogo.api.model.ItemDto;
import vahenrique.ms.loja.catalogo.api.model.ItemInputDto;
import vahenrique.ms.loja.catalogo.domain.model.Item;

@AllArgsConstructor
@Component
public class ItemMapper {

	private ModelMapper modelMapper;

	public ItemDto toDto(Item item) {
		return modelMapper.map(item, ItemDto.class);
	}

	public List<ItemDto> toCollectionDto(List<Item> items) {
		return items.stream().map(this::toDto).collect(Collectors.toList());
	}

	public Item toEntity(ItemInputDto itemInputDto) {
		return modelMapper.map(itemInputDto, Item.class);
	}
}
