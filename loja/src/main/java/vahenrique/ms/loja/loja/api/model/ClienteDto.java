package vahenrique.ms.loja.loja.api.model;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto {

	private UUID id;
	private String nome;
	private String cpf;
	private String endereco;
	private String email;
}
