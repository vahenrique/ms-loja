package vahenrique.ms.loja.loja.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import vahenrique.ms.loja.loja.api.model.InformacoesLojaDto;
import vahenrique.ms.loja.loja.api.model.InformacoesLojaInputDto;
import vahenrique.ms.loja.loja.domain.model.InformacoesLoja;

@AllArgsConstructor
@Component
public class InformacoesLojaMapper {

	private ModelMapper modelMapper;

	public InformacoesLojaDto toDto(InformacoesLoja informacoesLoja) {
		return modelMapper.map(informacoesLoja, InformacoesLojaDto.class);
	}

	public InformacoesLoja toEntity(InformacoesLojaInputDto informacoesLojaDto) {
		return modelMapper.map(informacoesLojaDto, InformacoesLoja.class);
	}
}
