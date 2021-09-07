package vahenrique.ms.loja.loja.api.model;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoItemDto {

	private UUID id;
	private UUID pedidoId;
	private UUID catalogoItemId;
	private String catalogoItemNome;
	private BigDecimal quantidade;
	private BigDecimal valorUnitario;
}
