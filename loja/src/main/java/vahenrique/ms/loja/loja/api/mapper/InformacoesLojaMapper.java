package vahenrique.ms.loja.loja.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import vahenrique.ms.loja.loja.api.model.InformacoesLojaDto;
import vahenrique.ms.loja.loja.domain.model.InformacoesLoja;

@AllArgsConstructor
@Component
public class InformacoesLojaMapper {

	private ModelMapper modelMapper;

	public InformacoesLojaDto toDto(InformacoesLoja informacoesLoja) {
		return modelMapper.map(informacoesLoja, InformacoesLojaDto.class);
	}

	public List<InformacoesLojaDto> toCollectionDto(List<InformacoesLoja> listInformacoesLoja) {
		return listInformacoesLoja.stream().map(this::toDto).collect(Collectors.toList());
	}

	public InformacoesLoja toEntity(InformacoesLojaDto informacoesLojaDto) {
		return modelMapper.map(informacoesLojaDto, InformacoesLoja.class);
	}
}
