package vahenrique.ms.loja.transportadora.api.model;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import vahenrique.ms.loja.transportadora.domain.enumeration.StatusTransporte;

@Getter
@Setter
public class TransporteNovoInputDto {

	@NotNull
	private UUID pedidoId;

	@NotBlank
	@Size(max = 120)
	private String enderecoRemetente;

	@NotBlank
	@Size(max = 120)
	private String enderecoDestinatario;

	@NotNull
	private StatusTransporte status;
}
