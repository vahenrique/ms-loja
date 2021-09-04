package vahenrique.ms.loja.loja.domain.vo;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class CatalogoItemVo {

	@EqualsAndHashCode.Include
	private UUID id;
	private String nome;
	private BigDecimal valor;
	private String categoriaNome;
}
