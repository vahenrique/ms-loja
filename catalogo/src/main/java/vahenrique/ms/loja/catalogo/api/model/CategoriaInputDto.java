package vahenrique.ms.loja.catalogo.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaInputDto {

	@NotBlank
	@Size(max = 60)
	private String nome;
}
