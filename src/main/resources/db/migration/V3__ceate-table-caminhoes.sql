create table caminhoes(

    id bigint not null auto_increment,
    nome_do_motorista varchar(100) not null,
    telefone varchar(20) not null,
    cpf varchar(14) not null unique,
    modelo varchar(100) not null,
    placa varchar(8) not null unique,
    tipo_do_caminhao varchar(15) not null,
    capacidade DECIMAL(5,3) not null,
    ativo tinyint DEFAULT 1,

    primary key(id)

);
