package vahenrique.ms.loja.loja.api.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import vahenrique.ms.loja.loja.api.mapper.InformacoesLojaMapper;
import vahenrique.ms.loja.loja.api.model.InformacoesLojaDto;
import vahenrique.ms.loja.loja.domain.model.InformacoesLoja;
import vahenrique.ms.loja.loja.domain.repository.InformacoesLojaRepository;
import vahenrique.ms.loja.loja.domain.service.InformacoesLojaService;

@AllArgsConstructor
@RestController
@RequestMapping("/infoloja")
public class InformacoesLojaController {

	private InformacoesLojaRepository informacoesLojaRepository;
	private InformacoesLojaService informacoesLojaService;
	private InformacoesLojaMapper informacoesLojaMapper;

	@GetMapping
	public List<InformacoesLojaDto> visualizar() {
		return informacoesLojaMapper.toCollectionDto(informacoesLojaRepository.findAll());
	}

	@PostMapping
	public ResponseEntity<Object> incluir(@Valid @RequestBody InformacoesLojaDto informacoesLojaDto) {
		if (informacoesLojaRepository.count() > 0) {
			return ResponseEntity.badRequest().body(new String("Somente permitido um registro neste cadastro."));
		}

		InformacoesLoja informacoesLoja = informacoesLojaService
				.save(informacoesLojaMapper.toEntity(informacoesLojaDto));

		return ResponseEntity.status(HttpStatus.CREATED).body(informacoesLojaMapper.toDto(informacoesLoja));
	}

	@PutMapping
	public ResponseEntity<InformacoesLojaDto> atualizar(@Valid @RequestBody InformacoesLojaDto informacoesLojaDto) {
		if (informacoesLojaRepository.count() == 0) {
			return ResponseEntity.notFound().build();
		}

		UUID idInformacoesLoja = informacoesLojaRepository.findAll().get(0).getId();
		InformacoesLoja informacoesLoja = informacoesLojaMapper.toEntity(informacoesLojaDto);
		informacoesLoja.setId(idInformacoesLoja);
		informacoesLoja = informacoesLojaRepository.save(informacoesLoja);

		return ResponseEntity.ok(informacoesLojaMapper.toDto(informacoesLoja));
	}

	@DeleteMapping
	public ResponseEntity<Void> deletar() {
		if (informacoesLojaRepository.count() == 0) {
			return ResponseEntity.notFound().build();
		}

		informacoesLojaService.delete();
		return ResponseEntity.noContent().build();
	}
}
