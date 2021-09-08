package vahenrique.ms.loja.transportadora.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import vahenrique.ms.loja.transportadora.api.mapper.TransporteMapper;
import vahenrique.ms.loja.transportadora.api.model.TransporteAtualizacaoDto;
import vahenrique.ms.loja.transportadora.api.model.TransporteDto;
import vahenrique.ms.loja.transportadora.api.model.TransporteNovoInputDto;
import vahenrique.ms.loja.transportadora.domain.model.Transporte;
import vahenrique.ms.loja.transportadora.domain.repository.TransporteRepository;
import vahenrique.ms.loja.transportadora.domain.service.TransporteService;

@AllArgsConstructor
@RestController
@RequestMapping("/transportes")
public class TransporteController {

	private TransporteRepository transporteRepository;
	private TransporteService transporteService;
	private TransporteMapper transporteMapper;

	@GetMapping
	public List<TransporteDto> listar() {
		return transporteMapper.toCollectionDto(transporteRepository.findAll());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TransporteDto incluir(@Valid @RequestBody TransporteNovoInputDto transporteNovoInputDto) {
		Transporte transporte = transporteMapper.toEntity(transporteNovoInputDto);
		return transporteMapper.toDto(transporteService.save(transporte));
	}

	@PutMapping("/{id}")
	public ResponseEntity<TransporteDto> atualizar(@PathVariable UUID id,
			@Valid @RequestBody TransporteAtualizacaoDto transporteAtualizacaoDto) {
		Optional<Transporte> optionalTransporte = transporteRepository.findById(id);

		if (!optionalTransporte.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Transporte transporte = optionalTransporte.get();
		transporte.setCodigoRastreio(transporteAtualizacaoDto.getCodigoRastreio());
		transporte.setStatus(transporteAtualizacaoDto.getStatus());
		transporte = transporteService.save(transporte);
		return ResponseEntity.ok(transporteMapper.toDto(transporte));
	}
}
