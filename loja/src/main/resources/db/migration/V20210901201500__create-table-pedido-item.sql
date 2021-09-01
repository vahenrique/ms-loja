create table pedido_item (
	id BINARY(16) NOT NULL PRIMARY KEY,
	pedido_id BINARY(16) NOT NULL,
	catalogo_item_id BINARY(16) NOT NULL,
	nome VARCHAR(60) NOT NUL,
	quantidade DECIMAL(20,2) NOT NULL DEFAULT 0.00,
	valor_unitario DECIMAL(20,2) NOT NULL DEFAULT 0.00,
    FOREIGN KEY (pedido_id) REFERENCES pedido(id)
);