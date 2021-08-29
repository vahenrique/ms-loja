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
import vahenrique.ms.loja.catalogo.domain.model.Categoria;
import vahenrique.ms.loja.catalogo.domain.repository.CategoriaRepository;
import vahenrique.ms.loja.catalogo.domain.service.CategoriaService;

@AllArgsConstructor
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	private CategoriaRepository categoriaRepository;
	private CategoriaService categoriaService;

	@GetMapping
	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Categoria incluir(@RequestBody Categoria categoria) {
		return categoriaService.save(categoria);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Categoria> atualizar(@PathVariable UUID id, @RequestBody Categoria categoria) {
		if (!categoriaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		categoria.setId(id);
		categoriaService.save(categoria);

		return ResponseEntity.ok(categoria);
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
