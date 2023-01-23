create table votacao(
    id int primary key not null auto_increment,
    escolha char(1),
    sessao_id int,

    constraint FK_votacao_sessao FOREIGN KEY(sessao_id)
    references sessao(id)
);
