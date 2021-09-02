package vahenrique.ms.loja.transportadora.api.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import vahenrique.ms.loja.transportadora.api.mapper.TransporteMapper;
import vahenrique.ms.loja.transportadora.api.model.TransporteDto;
import vahenrique.ms.loja.transportadora.api.model.TransporteNovoInputDto;
import vahenrique.ms.loja.transportadora.domain.model.Transporte;
import vahenrique.ms.loja.transportadora.domain.service.TransporteService;

@AllArgsConstructor
@RestController
@RequestMapping("/transportes")
public class TransporteController {

	private TransporteService transporteService;
	private TransporteMapper transporteMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TransporteDto incluir(@Valid @RequestBody TransporteNovoInputDto transporteNovoInputDto) {
		Transporte transporte = transporteMapper.toEntity(transporteNovoInputDto);
		return transporteMapper.toDto(transporteService.save(transporte));
	}
}
