package vahenrique.ms.loja.loja.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import vahenrique.ms.loja.loja.api.model.PedidoItemDto;
import vahenrique.ms.loja.loja.api.model.PedidoItemInputDto;
import vahenrique.ms.loja.loja.domain.model.PedidoItem;

@AllArgsConstructor
@Component
public class PedidoItemMapper {

	private ModelMapper modelMapper;

	public PedidoItemDto toDto(PedidoItem pedidoItem) {
		return modelMapper.map(pedidoItem, PedidoItemDto.class);
	}

	public List<PedidoItemDto> toCollectionDto(List<PedidoItem> pedidoItems) {
		return pedidoItems.stream().map(this::toDto).collect(Collectors.toList());
	}

	public PedidoItem toEntity(PedidoItemInputDto pedidoItemInputDto) {
		return modelMapper.map(pedidoItemInputDto, PedidoItem.class);
	}
}
