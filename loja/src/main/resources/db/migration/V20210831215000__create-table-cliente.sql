create table cliente (
	id BINARY(16) NOT NULL PRIMARY KEY,
	nome varchar(60) NOT NULL,
	cpf  char(11) NOT NULL,
	endereco VARCHAR(120) NOT NULL,
	email VARCHAR(60) NOT NULL
);