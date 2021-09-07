package vahenrique.ms.loja.loja.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import vahenrique.ms.loja.loja.domain.service.CatalogoService;
import vahenrique.ms.loja.loja.domain.vo.CatalogoItemVo;

@AllArgsConstructor
@RestController
@RequestMapping("/catalogo")
public class CatalogoController {

	private CatalogoService catalogoService;

	@GetMapping
	public List<CatalogoItemVo> listar() {
		return catalogoService.getItensDoCatalogo();
	}

	@GetMapping("/{catalogoItemId}")
	public CatalogoItemVo visualizarItem(@PathVariable UUID catalogoItemId) {
		return catalogoService.getItemDoCatalogo(catalogoItemId);
	}
}
