INSERT INTO cliente(nome, cpf) VALUES ('Josefa Barbosa', '234567809');
INSERT INTO cliente(nome, cpf) VALUES ('Otávio Mesquita', '234657812');

INSERT INTO empresa(nome, cnpj) VALUES ('Mercado Samambaia', '232134/0001');
INSERT INTO empresa(nome, cnpj) VALUES ('Mecânica Pit Stop', '452144/0001');

INSERT INTO transacao(empresa_id, cliente_id, valor, tipo_transacao, valor_taxa) VALUES (1, 2, 100.0, 0, 0.1);
INSERT INTO transacao(empresa_id, cliente_id, valor, tipo_transacao, valor_taxa) VALUES (2, 1, 1500.0, 1, 0.1);
INSERT INTO transacao(empresa_id, cliente_id, valor, tipo_transacao, valor_taxa) VALUES (1, 1, 300.0, 1, 0.1);