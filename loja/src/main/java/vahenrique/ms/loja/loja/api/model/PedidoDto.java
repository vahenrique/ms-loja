package vahenrique.ms.loja.loja.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import vahenrique.ms.loja.loja.domain.enumeration.StatusPedido;

@Getter
@Setter
public class PedidoDto {

	private UUID id;
	private OffsetDateTime dtGeracao;
	private StatusPedido status;
	private BigDecimal valorFrete;
	private UUID clienteId;
	private Set<PedidoItemDto> pedidoItems;
	private UUID transporteId;
}
