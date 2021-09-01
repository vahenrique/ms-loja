package vahenrique.ms.loja.catalogo.api.model;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemInputDto {

	private String nome;
	private BigDecimal valor;
	private UUID idCategoria;
}