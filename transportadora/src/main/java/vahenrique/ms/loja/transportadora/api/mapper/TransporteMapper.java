package vahenrique.ms.loja.transportadora.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import vahenrique.ms.loja.transportadora.api.model.TransporteDto;
import vahenrique.ms.loja.transportadora.api.model.TransporteNovoInputDto;
import vahenrique.ms.loja.transportadora.domain.model.Transporte;

@AllArgsConstructor
@Component
public class TransporteMapper {

	private ModelMapper modelMapper;

	public TransporteDto toDto(Transporte transporte) {
		return modelMapper.map(transporte, TransporteDto.class);
	}

	public List<TransporteDto> toCollectionDto(List<Transporte> transportes) {
		return transportes.stream().map(this::toDto).collect(Collectors.toList());
	}

	public Transporte toEntity(TransporteNovoInputDto transporteNovoInputDto) {
		return modelMapper.map(transporteNovoInputDto, Transporte.class);
	}
}
