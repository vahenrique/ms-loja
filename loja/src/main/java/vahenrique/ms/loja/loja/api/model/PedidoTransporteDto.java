package vahenrique.ms.loja.loja.api.model;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import vahenrique.ms.loja.loja.domain.enumeration.StatusPedido;

@Getter
@Setter
public class PedidoTransporteDto {

	@NotNull
	private StatusPedido status;
}
