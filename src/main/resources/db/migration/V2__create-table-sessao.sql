create table sessao(
    id int primary key not null auto_increment,
    data_criacao timestamp not null,
    data_encerramento timestamp,
    pauta_id int,

    constraint FK_sessao_pauta FOREIGN KEY(pauta_id)
    references pauta(id)
);
