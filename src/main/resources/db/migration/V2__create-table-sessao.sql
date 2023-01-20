create table sessao(
    id int primary key not null,
    pauta_id int,
    data_criacao timestamp not null default NOW(),
    data_encerramento timestamp,

    constraint FK_sessao_pauta FOREIGN KEY(pauta_id)
    references pauta(id)
);

CREATE SEQUENCE sessao_id_sequence
OWNED BY sessao.id;

alter table sessao alter id set default nextval('sessao_id_sequence'::regclass);