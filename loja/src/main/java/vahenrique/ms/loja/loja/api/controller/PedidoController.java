package vahenrique.ms.loja.loja.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import vahenrique.ms.loja.loja.api.mapper.PedidoMapper;
import vahenrique.ms.loja.loja.api.model.PedidoNovoDto;
import vahenrique.ms.loja.loja.domain.model.Pedido;
import vahenrique.ms.loja.loja.domain.service.PedidoService;

@AllArgsConstructor
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	private PedidoService pedidoService;
	private PedidoMapper pedidoMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoNovoDto novo() {
		Pedido pedido = pedidoService.save(pedidoService.novo());
		return pedidoMapper.toNovoDto(pedido);
	}
}
