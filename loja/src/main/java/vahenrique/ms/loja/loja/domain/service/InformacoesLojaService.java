package vahenrique.ms.loja.loja.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import vahenrique.ms.loja.loja.domain.model.InformacoesLoja;
import vahenrique.ms.loja.loja.domain.repository.InformacoesLojaRepository;

@AllArgsConstructor
@Service
public class InformacoesLojaService {

	private InformacoesLojaRepository informacoesLojaRepository;

	@Transactional
	public InformacoesLoja save(InformacoesLoja informacoesLoja) {
		return informacoesLojaRepository.save(informacoesLoja);
	}

	@Transactional
	public void delete() {
		informacoesLojaRepository.deleteAll();
	}
}
