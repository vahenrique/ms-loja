create table item (
	id BINARY(16) NOT NULL PRIMARY KEY,
	nome varchar(60) NOT NULL,
    valor decimal(20,2) NOT NULL DEFAULT 0.00,
    categoria_id BINARY(16) NOT NULL,
    FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);
