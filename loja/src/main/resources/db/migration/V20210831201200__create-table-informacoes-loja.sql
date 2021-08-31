create table informacoes_loja (
	id BINARY(16) NOT NULL PRIMARY KEY,
	nome varchar(60) NOT NULL,
	cnpj  char(14) NOT NULL,
	endereco VARCHAR(120) NOT NULL,
	email VARCHAR(60) NOT NULL
);
