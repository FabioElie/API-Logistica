create table clientes(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    cnpj_cpf varchar(18) not null unique,
    telefone varchar(20) not null unique,
    numero varchar(20) not null,
    rua varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    cidade varchar(50) not null,
    uf char(2) not null,
    complemento varchar(100),
    ativo tinyint,

    primary key(id)

);
