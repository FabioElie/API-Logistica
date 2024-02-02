create table vendedores(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    telefone varchar(14) not null,
    cpf varchar(14) not null unique,
    ativo tinyint DEFAULT 1,

    primary key(id)

);
