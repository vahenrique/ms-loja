package vahenrique.ms.loja.transportadora.domain.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import vahenrique.ms.loja.transportadora.domain.model.Transporte;
import vahenrique.ms.loja.transportadora.domain.repository.TransporteRepository;

@AllArgsConstructor
@Service
public class TransporteService {

	private TransporteRepository transporteRepository;

	@Transactional
	public Transporte save(Transporte transporte) {
		return transporteRepository.save(transporte);
	}

	@Transactional
	public void delete(UUID id) {
		transporteRepository.deleteById(id);
	}
}
