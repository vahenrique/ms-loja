create table transporte (
	id BINARY(16) NOT NULL PRIMARY KEY,
	pedido_id BINARY(16) NOT NULL,
	endereco_remetente VARCHAR(120) NOT NULL,
	endereco_destinatario VARCHAR(120) NOT NULL,
	status ENUM('AGUARDANDO', 'ENVIADO', 'ENTREGUE') NOT NULL,
	codigo_rastreio CHAR(13)
);