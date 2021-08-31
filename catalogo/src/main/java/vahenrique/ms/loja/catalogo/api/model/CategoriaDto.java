package vahenrique.ms.loja.catalogo.api.model;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaDto {

	private UUID id;
	private String nome;
}
