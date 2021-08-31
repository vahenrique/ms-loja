package vahenrique.ms.loja.catalogo.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import vahenrique.ms.loja.catalogo.api.model.CategoriaDto;
import vahenrique.ms.loja.catalogo.api.model.CategoriaInputDto;
import vahenrique.ms.loja.catalogo.domain.model.Categoria;

@AllArgsConstructor
@Component
public class CategoriaMapper {

	private ModelMapper modelMapper;

	public CategoriaDto toDto(Categoria categoria) {
		return modelMapper.map(categoria, CategoriaDto.class);
	}

	public List<CategoriaDto> toCollectionDto(List<Categoria> categorias) {
		return categorias.stream().map(this::toDto).collect(Collectors.toList());
	}

	public Categoria toEntity(CategoriaInputDto categoriaInputDto) {
		return modelMapper.map(categoriaInputDto, Categoria.class);
	}
}
