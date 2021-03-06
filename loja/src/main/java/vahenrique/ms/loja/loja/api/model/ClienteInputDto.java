package vahenrique.ms.loja.loja.api.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteInputDto {

	@NotBlank
	@Size(max = 60)
	private String nome;

	@NotBlank
	@CPF
	@Size(max = 11)
	private String cpf;

	@NotBlank
	@Size(max = 120)
	private String endereco;

	@NotBlank
	@Email
	@Size(max = 60)
	private String email;
}
