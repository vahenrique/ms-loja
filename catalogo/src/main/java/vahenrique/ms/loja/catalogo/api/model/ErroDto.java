package vahenrique.ms.loja.catalogo.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErroDto {

	private String campo;
	private String mensagem;
}
