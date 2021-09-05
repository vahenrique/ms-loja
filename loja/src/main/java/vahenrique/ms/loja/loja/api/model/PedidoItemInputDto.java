package vahenrique.ms.loja.loja.api.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoItemInputDto {

	@NotNull
	private UUID pedidoId;

	@NotNull
	private UUID catalogoItemId;

	@NotNull
	@Positive
	private BigDecimal quantidade;

	@NotNull
	private BigDecimal valorUnitario;
}
