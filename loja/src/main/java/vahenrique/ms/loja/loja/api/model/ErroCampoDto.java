package vahenrique.ms.loja.loja.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErroCampoDto {

	private String campo;
	private String mensagem;
}