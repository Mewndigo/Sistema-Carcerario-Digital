-- Dados iniciais para testes locais.
-- Com GenerationType.IDENTITY, os IDs sao gerados pelo banco.
-- Como o ddl-auto esta em create, cada tabela comeca em 1 ao recriar o schema.

-- Pessoas
INSERT INTO tb_pessoa (nome, cpf) VALUES ('Joao Silva', '12345678901');
INSERT INTO tb_pessoa (nome, cpf) VALUES ('Maria Oliveira', '98765432100');
INSERT INTO tb_pessoa (nome, cpf) VALUES ('Carlos Santos', '11122233344');
INSERT INTO tb_pessoa (nome, cpf) VALUES ('Ana Souza', '55566677788');

-- Tipos de atividade
INSERT INTO tb_tipo_atividade (nome) VALUES ('Trabalho interno');
INSERT INTO tb_tipo_atividade (nome) VALUES ('Estudo regular');
INSERT INTO tb_tipo_atividade (nome) VALUES ('Atendimento psicologico');
INSERT INTO tb_tipo_atividade (nome) VALUES ('Visita monitorada');

-- Tipos de ocorrencia
INSERT INTO tb_tipo_ocorrencia (nome) VALUES ('Indisciplina');
INSERT INTO tb_tipo_ocorrencia (nome) VALUES ('Atendimento medico');
INSERT INTO tb_tipo_ocorrencia (nome) VALUES ('Transferencia');
INSERT INTO tb_tipo_ocorrencia (nome) VALUES ('Elogio de conduta');

-- Condenacoes
INSERT INTO tb_condenacao (descricao, data_entrada, data_saida, situacao, pessoa_id) VALUES ('Condenacao por furto simples', '2025-01-10 08:30:00', '2027-01-10 08:30:00', 'ATIVO', 1);
INSERT INTO tb_condenacao (descricao, data_entrada, data_saida, situacao, pessoa_id) VALUES ('Condenacao por roubo', '2025-02-15 09:00:00', '2029-02-15 09:00:00', 'ATIVO', 2);
INSERT INTO tb_condenacao (descricao, data_entrada, data_saida, situacao, pessoa_id) VALUES ('Condenacao por receptacao', '2025-03-20 10:15:00', '2028-03-20 10:15:00', 'ISOLAMENTO', 3);
INSERT INTO tb_condenacao (descricao, data_entrada, data_saida, situacao, pessoa_id) VALUES ('Condenacao por dano ao patrimonio', '2025-04-25 14:45:00', '2026-10-25 14:45:00', 'ATIVO', 4);

-- Ocorrencias
INSERT INTO tb_ocorrencia (data_registro, descricao, tipo_ocorrencia_id, condenacao_id) VALUES ('2026-01-05 08:00:00', 'Discussao registrada no patio.', 1, 1);
INSERT INTO tb_ocorrencia (data_registro, descricao, tipo_ocorrencia_id, condenacao_id) VALUES ('2026-01-12 11:30:00', 'Encaminhamento para avaliacao medica.', 2, 2);
INSERT INTO tb_ocorrencia (data_registro, descricao, tipo_ocorrencia_id, condenacao_id) VALUES ('2026-02-03 15:20:00', 'Solicitacao de transferencia para outra ala.', 3, 3);
INSERT INTO tb_ocorrencia (data_registro, descricao, tipo_ocorrencia_id, condenacao_id) VALUES ('2026-02-18 09:10:00', 'Boa conduta durante atividade coletiva.', 4, 4);

-- Registros de atividade
INSERT INTO tb_registro_atividade (data_registro, descricao, tipo_atividade_id, condenacao_id, pessoa_id) VALUES ('2026-03-01 08:00:00', 'Participou de trabalho na cozinha.', 1, 1, 1);
INSERT INTO tb_registro_atividade (data_registro, descricao, tipo_atividade_id, condenacao_id, pessoa_id) VALUES ('2026-03-02 13:30:00', 'Frequentou aula de alfabetizacao.', 2, 2, 2);
INSERT INTO tb_registro_atividade (data_registro, descricao, tipo_atividade_id, condenacao_id, pessoa_id) VALUES ('2026-03-03 10:00:00', 'Atendimento psicologico individual.', 3, 3, 3);
INSERT INTO tb_registro_atividade (data_registro, descricao, tipo_atividade_id, condenacao_id, pessoa_id) VALUES ('2026-03-04 14:00:00', 'Visita monitorada registrada sem incidentes.', 4, 4, 4);
