package vahenrique.ms.loja.loja.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import vahenrique.ms.loja.loja.domain.model.Cliente;
import vahenrique.ms.loja.loja.domain.repository.ClienteRepository;

@AllArgsConstructor
@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Transactional
	public void delete(UUID id) {
		clienteRepository.deleteById(id);
	}
}
