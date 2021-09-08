package vahenrique.ms.loja.transportadora.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import vahenrique.ms.loja.transportadora.domain.enumeration.StatusTransporte;

@Getter
@Setter
public class TransporteAtualizacaoDto {

	@NotNull
	private StatusTransporte status;

	@NotBlank
	@Size(min = 13, max = 13)
	private String codigoRastreio;
}
