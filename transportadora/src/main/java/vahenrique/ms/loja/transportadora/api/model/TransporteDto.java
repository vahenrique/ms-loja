package vahenrique.ms.loja.transportadora.api.model;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import vahenrique.ms.loja.transportadora.domain.enumeration.StatusTransporte;

@Getter
@Setter
public class TransporteDto {

	private UUID id;
	private UUID pedidoId;
	private String enderecoRemetente;
	private String enderecoDestinatario;
	private StatusTransporte status;
	private String codigoRastreio;
}
