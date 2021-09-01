package vahenrique.ms.loja.loja.api.controller;

import java.util.List;
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
import vahenrique.ms.loja.loja.api.mapper.ClienteMapper;
import vahenrique.ms.loja.loja.api.model.ClienteDto;
import vahenrique.ms.loja.loja.api.model.ClienteInputDto;
import vahenrique.ms.loja.loja.domain.model.Cliente;
import vahenrique.ms.loja.loja.domain.repository.ClienteRepository;
import vahenrique.ms.loja.loja.domain.service.ClienteService;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private ClienteRepository clienteRepository;
	private ClienteService clienteService;
	private ClienteMapper clienteMapper;

	@GetMapping
	public List<ClienteDto> listar() {
		return clienteMapper.toCollectionDto(clienteRepository.findAll());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteDto incluir(@Valid @RequestBody ClienteInputDto clienteInputDto) {
		Cliente cliente = clienteService.save(clienteMapper.toEntity(clienteInputDto));
		return clienteMapper.toDto(cliente);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteDto> atualizar(@PathVariable UUID id,
			@Valid @RequestBody ClienteInputDto clienteInputDto) {
		if (!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		Cliente cliente = clienteMapper.toEntity(clienteInputDto);
		cliente.setId(id);
		cliente = clienteService.save(cliente);

		return ResponseEntity.ok(clienteMapper.toDto(cliente));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable UUID id) {
		if (!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		clienteService.delete(id);

		return ResponseEntity.noContent().build();
	}
}
