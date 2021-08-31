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
import vahenrique.ms.loja.catalogo.api.mapper.CategoriaMapper;
import vahenrique.ms.loja.catalogo.api.model.CategoriaDto;
import vahenrique.ms.loja.catalogo.api.model.CategoriaInputDto;
import vahenrique.ms.loja.catalogo.domain.model.Categoria;
import vahenrique.ms.loja.catalogo.domain.repository.CategoriaRepository;
import vahenrique.ms.loja.catalogo.domain.service.CategoriaService;

@AllArgsConstructor
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	private CategoriaRepository categoriaRepository;
	private CategoriaService categoriaService;
	private CategoriaMapper categoriaMapper;

	@GetMapping
	public List<CategoriaDto> listar() {
		return categoriaMapper.toCollectionDto(categoriaRepository.findAll());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoriaDto incluir(@RequestBody CategoriaInputDto categoriaInputDto) {
		Categoria categoria = categoriaService.save(categoriaMapper.toEntity(categoriaInputDto));
		return categoriaMapper.toDto(categoria);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoriaDto> atualizar(@PathVariable UUID id,
			@RequestBody CategoriaInputDto categoriaInputDto) {
		if (!categoriaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		Categoria categoria = categoriaMapper.toEntity(categoriaInputDto);
		categoria.setId(id);
		categoria = categoriaService.save(categoria);

		return ResponseEntity.ok(categoriaMapper.toDto(categoria));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable UUID id) {
		if (!categoriaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		categoriaService.delete(id);

		return ResponseEntity.noContent().build();
	}
}
