package vahenrique.ms.loja.transportadora.domain.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import vahenrique.ms.loja.transportadora.domain.enumeration.StatusTransporte;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Transporte implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private UUID id;

	private UUID pedidoId;
	private String enderecoRemetente;
	private String enderecoDestinatario;
	private StatusTransporte status;
	private String codigoRastreio;
}
