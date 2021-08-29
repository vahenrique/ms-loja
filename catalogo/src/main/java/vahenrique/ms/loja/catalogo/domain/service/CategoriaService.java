package vahenrique.ms.loja.catalogo.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import vahenrique.ms.loja.catalogo.domain.model.Categoria;
import vahenrique.ms.loja.catalogo.domain.repository.CategoriaRepository;

@AllArgsConstructor
@Service
public class CategoriaService {

	private CategoriaRepository categoriaRepository;

	@Transactional
	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	@Transactional
	public void delete(UUID id) {
		categoriaRepository.deleteById(id);
	}
}
