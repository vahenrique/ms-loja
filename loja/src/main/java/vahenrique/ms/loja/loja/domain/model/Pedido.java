package vahenrique.ms.loja.loja.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import vahenrique.ms.loja.loja.domain.enumeration.StatusPedido;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private UUID id;

	private OffsetDateTime dtGeracao;

	@Enumerated(EnumType.STRING)
	private StatusPedido status;

	private BigDecimal valorFrete;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = true)
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido")
	private Set<PedidoItem> pedidoItems;
}
