package vahenrique.ms.loja.catalogo.api.model;

import java.time.OffsetDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErroDto {

	private Integer status;
	private OffsetDateTime dataHora;
	private String titulo;
	List<ErroCampoDto> campos;
}
