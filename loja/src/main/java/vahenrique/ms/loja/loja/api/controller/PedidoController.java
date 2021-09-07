package vahenrique.ms.loja.loja.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

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
import vahenrique.ms.loja.loja.api.mapper.PedidoMapper;
import vahenrique.ms.loja.loja.api.model.PedidoDto;
import vahenrique.ms.loja.loja.api.model.PedidoFinalizacaoDto;
import vahenrique.ms.loja.loja.api.model.PedidoNovoDto;
import vahenrique.ms.loja.loja.domain.enumeration.StatusPedido;
import vahenrique.ms.loja.loja.domain.model.Cliente;
import vahenrique.ms.loja.loja.domain.model.InformacoesLoja;
import vahenrique.ms.loja.loja.domain.model.Pedido;
import vahenrique.ms.loja.loja.domain.repository.ClienteRepository;
import vahenrique.ms.loja.loja.domain.repository.InformacoesLojaRepository;
import vahenrique.ms.loja.loja.domain.repository.PedidoRepository;
import vahenrique.ms.loja.loja.domain.service.PedidoService;
import vahenrique.ms.loja.loja.domain.service.TransportadoraService;
import vahenrique.ms.loja.loja.domain.vo.TransporteVo;

@AllArgsConstructor
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	private PedidoRepository pedidoRepository;
	private ClienteRepository clienteRepository;
	private InformacoesLojaRepository informacoesLojaRepository;
	private PedidoService pedidoService;
	private TransportadoraService transportadoraService;
	private PedidoMapper pedidoMapper;

	@GetMapping
	public List<PedidoDto> listar() {
		return pedidoMapper.toCollectionDto(pedidoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PedidoDto> visualizar(@PathVariable UUID id) {
		Optional<Pedido> optionalPedido = pedidoRepository.findById(id);

		if (!optionalPedido.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(pedidoMapper.toDto(optionalPedido.get()));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoNovoDto novo() {
		Pedido pedido = pedidoService.save(pedidoService.novo());
		return pedidoMapper.toNovoDto(pedido);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> finalizar(@PathVariable UUID id,
			@Valid @RequestBody PedidoFinalizacaoDto pedidoFinalizacaoDto) {
		Optional<Pedido> optionalPedido = pedidoRepository.findById(id);

		if (!optionalPedido.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Optional<Cliente> optionalCliente = clienteRepository.findById(pedidoFinalizacaoDto.getClienteId());
		if (!optionalCliente.isPresent()) {
			return ResponseEntity.badRequest().body(new String("Cliente não localizado."));
		}

		if (informacoesLojaRepository.count() == 0) {
			return ResponseEntity.badRequest().body(new String("Informações da Loja não localizadas."));
		}

		InformacoesLoja infoLoja = informacoesLojaRepository.findAll().get(0);

		Pedido pedido = optionalPedido.get();
		pedido.setValorFrete(pedidoFinalizacaoDto.getValorFrete());
		pedido.setCliente(optionalCliente.get());
		pedido.setStatus(StatusPedido.FINALIZADO);
		pedido = pedidoService.save(pedido);

		TransporteVo transporteVo = transportadoraService.enviar(pedido.getId(), pedido.getCliente().getEndereco(),
				infoLoja.getEndereco());
		PedidoDto pedidoDto = pedidoMapper.toDto(pedido);
		pedidoDto.setTransporteId(transporteVo == null ? null : transporteVo.getId());

		return ResponseEntity.ok(pedidoDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable UUID id) {
		if (!pedidoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		pedidoService.delete(id);

		return ResponseEntity.noContent().build();
	}
}
