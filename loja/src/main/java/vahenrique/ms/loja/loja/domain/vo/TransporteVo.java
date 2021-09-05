package vahenrique.ms.loja.loja.domain.vo;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import vahenrique.ms.loja.loja.domain.enumeration.StatusTransporte;

@Getter
@Setter
public class TransporteVo {

	private UUID id;
	private UUID pedidoId;
	private String enderecoRemetente;
	private String enderecoDestinatario;
	private StatusTransporte status;
	private String codigoRastreio;
}
