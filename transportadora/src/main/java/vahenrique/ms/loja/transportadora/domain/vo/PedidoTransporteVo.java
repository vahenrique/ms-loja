package vahenrique.ms.loja.transportadora.domain.vo;

import lombok.Getter;
import lombok.Setter;
import vahenrique.ms.loja.transportadora.domain.enumeration.StatusPedido;

@Getter
@Setter
public class PedidoTransporteVo {

	private StatusPedido status;
}
