package vahenrique.ms.loja.loja.api.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoFinalizacaoDto {

	@NotNull
	@PositiveOrZero
	private BigDecimal valorFrete;

	@NotNull
	private UUID clienteId;
}
