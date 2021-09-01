package vahenrique.ms.loja.loja.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import vahenrique.ms.loja.loja.api.model.ClienteDto;
import vahenrique.ms.loja.loja.api.model.ClienteInputDto;
import vahenrique.ms.loja.loja.domain.model.Cliente;

@AllArgsConstructor
@Component
public class ClienteMapper {

	private ModelMapper modelMapper;

	public ClienteDto toDto(Cliente cliente) {
		return modelMapper.map(cliente, ClienteDto.class);
	}

	public List<ClienteDto> toCollectionDto(List<Cliente> clientes) {
		return clientes.stream().map(this::toDto).collect(Collectors.toList());
	}

	public Cliente toEntity(ClienteInputDto clienteInputDto) {
		return modelMapper.map(clienteInputDto, Cliente.class);
	}
}
