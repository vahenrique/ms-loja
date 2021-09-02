package vahenrique.ms.loja.loja.api.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoItemInputDto {

	@NotNull
	private UUID pedidoId;

	@NotNull
	private UUID catalogoItemId;

	@NotBlank
	@Size(max = 60)
	private String nome;

	@NotNull
	@Positive
	private BigDecimal quantidade;

	@NotNull
	private BigDecimal valorUnitario;
}
