package vahenrique.ms.loja.loja.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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
}
