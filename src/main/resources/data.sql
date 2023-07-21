CREATE TABLE departamentos
(
    id IDENTITY NOT NULL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

CREATE TABLE pessoas
(
    id IDENTITY NOT NULL PRIMARY KEY,
    nome            VARCHAR(50) NOT NULL,
    i_departamentos BIGINT      NOT NULL,

    CONSTRAINT FK_DEPARTAMENTOS_PESSOAS
        FOREIGN KEY (i_departamentos)
            REFERENCES departamentos (id)
);

CREATE TABLE tarefas
(
    id IDENTITY NOT NULL PRIMARY KEY,
    titulo          VARCHAR(50)              NOT NULL,
    descricao       VARCHAR(200)              NOT NULL,
    prazo           TIMESTAMP WITH TIME ZONE NOT NULL,
    i_departamentos BIGINT                   NOT NULL,
    duracao         INT,
    i_pessoas       BIGINT,
    fl_finalizado   BOOLEAN                  not null,

    CONSTRAINT FK_DEPARTAMENTOS_TAREFAS
        FOREIGN KEY (i_departamentos)
            REFERENCES departamentos (id),

    CONSTRAINT FK_PESSOAS_TAREFAS
        FOREIGN KEY (i_pessoas)
            REFERENCES pessoas (id)

);


insert into departamentos (id, nome)
values (1, 'Financeiro');
insert into departamentos (id, nome)
values (2, 'Comercial');
insert into departamentos (id, nome)
values (3, 'Desenvolvimento');

insert into pessoas (id, nome, i_departamentos)
values (1, 'Camila', 1);
insert into pessoas (id, nome, i_departamentos)
values (2, 'Pedro', 2);
insert into pessoas (id, nome, i_departamentos)
values (3, 'Fabiano', 3);
insert into pessoas (id, nome, i_departamentos)
values (4, 'Raquel', 3);
insert into pessoas (id, nome, i_departamentos)
values (5, 'Patricia', 3);
insert into pessoas (id, nome, i_departamentos)
values (6, 'Joaquim', 1);


insert into tarefas (id, titulo, descricao, prazo, i_departamentos, duracao, i_pessoas, fl_finalizado)
values (1001, 'Validar NF Janeiro', 'Validar notas recebidas no mês de Janeiro', '2022-02-15 00:00:00+00', 1, 14, 1,
        true);

insert into tarefas (id, titulo, descricao, prazo, i_departamentos, duracao, i_pessoas, fl_finalizado)
values (1002, 'Bug 352', 'Corrigir bug 352 na versão 1.25', '2022-05-10 00:00:00+00', 3, 25, null,
        false);

insert into tarefas (id, titulo, descricao, prazo, i_departamentos, duracao, i_pessoas, fl_finalizado)
values (1003, 'Liberação da versão 1.24', 'Disponibilizar pacote para testes', '2022-02-02 00:00:00+00', 3, 2, 3,
        false);

insert into tarefas (id, titulo, descricao, prazo, i_departamentos, duracao, i_pessoas, fl_finalizado)
values (1004, 'Reunião A', 'Reunião com cliente A para apresentação do produto', '2022-02-05 00:00:00+00', 2, 5, null,
        false);

insert into tarefas (id, titulo, descricao, prazo, i_departamentos, duracao, i_pessoas, fl_finalizado)
values (1005, 'Reunião final', 'Fechamento contrato', '2022-03-28 00:00:00+00', 2, 6, null,
        false);

insert into tarefas (id, titulo, descricao, prazo, i_departamentos, duracao, i_pessoas, fl_finalizado)
values (1006, 'Pagamento 01/2022', 'Realizar pagamento dos fornecedores', '2022-01-31 00:00:00+00', 1, 6, 1,
        true);

insert into tarefas (id, titulo, descricao, prazo, i_departamentos, duracao, i_pessoas, fl_finalizado)
values (1007, 'Bug 401', 'Corrigir bug 401 na versão 1.20', '2022-02-01 00:00:00+00', 3, 2, 4,
        true);

insert into tarefas (id, titulo, descricao, prazo, i_departamentos, duracao, i_pessoas, fl_finalizado)
values (1008, 'Bug 399', 'Corrigir bug 399 na versão 1.20', '2022-01-28 00:00:00+00', 3, 6, 5,
        true);

insert into tarefas (id, titulo, descricao, prazo, i_departamentos, duracao, i_pessoas, fl_finalizado)
values (1009, 'Reunião B', 'Reunião com cliente B para apresentação do produto', '2022-01-31 00:00:00+00', 2, 5, 2,
        true);

insert into tarefas (id, titulo, descricao, prazo, i_departamentos, duracao, i_pessoas, fl_finalizado)
values (1010, 'Validar NF Fevereiro', 'Validar notas recebidas no mês de Fevereiro', '2022-03-15 00:00:00+00', 1, 14, 6,
        false);

