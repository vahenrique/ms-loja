package vahenrique.ms.loja.loja.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import vahenrique.ms.loja.loja.api.model.PedidoDto;
import vahenrique.ms.loja.loja.api.model.PedidoNovoDto;
import vahenrique.ms.loja.loja.domain.model.Pedido;

@AllArgsConstructor
@Component
public class PedidoMapper {

	private ModelMapper modelMapper;

	public PedidoNovoDto toNovoDto(Pedido pedido) {
		return modelMapper.map(pedido, PedidoNovoDto.class);
	}

	public PedidoDto toDto(Pedido pedido) {
		return modelMapper.map(pedido, PedidoDto.class);
	}

	public List<PedidoDto> toCollectionDto(List<Pedido> pedidos) {
		return pedidos.stream().map(this::toDto).collect(Collectors.toList());
	}
}
