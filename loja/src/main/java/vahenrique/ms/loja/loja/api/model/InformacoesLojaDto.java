package vahenrique.ms.loja.loja.api.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InformacoesLojaDto {

	@NotBlank
	@Size(max = 60)
	private String nome;

	@NotBlank
	@CNPJ
	@Size(max = 14)
	private String cnpj;

	@NotBlank
	@Size(max = 120)
	private String endereco;

	@NotBlank
	@Email
	@Size(max = 60)
	private String email;
}
