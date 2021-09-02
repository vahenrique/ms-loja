package vahenrique.ms.loja.loja.api.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoItemQuantidadeInputDto {

	@NotNull
	@Positive
	private BigDecimal quantidade;
}
