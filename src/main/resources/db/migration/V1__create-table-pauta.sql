create table pauta(
    id int primary key not null,
    nome varchar(150) not null
);

CREATE SEQUENCE pauta_id_sequence
OWNED BY pauta.id;

alter table pauta alter id set default nextval('pauta_id_sequence'::regclass);