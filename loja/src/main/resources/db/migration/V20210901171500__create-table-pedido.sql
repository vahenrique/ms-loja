create table pedido (
	id BINARY(16) NOT NULL PRIMARY KEY,
	dt_geracao TIMESTAMP NOT NULL,
	status ENUM('PENDENTE', 'FINALIZADO', 'ENVIADO', 'ENTREGUE') NOT NULL,
	valor_frete DECIMAL(20,2) NOT NULL DEFAULT 0.00,
	cliente_id BINARY(16),
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);