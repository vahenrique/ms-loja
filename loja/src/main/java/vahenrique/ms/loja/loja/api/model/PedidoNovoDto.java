package vahenrique.ms.loja.loja.api.model;

import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import vahenrique.ms.loja.loja.domain.enumeration.StatusPedido;

@Getter
@Setter
public class PedidoNovoDto {

	private UUID id;
	private OffsetDateTime dtGeracao;
	private StatusPedido status;
}
