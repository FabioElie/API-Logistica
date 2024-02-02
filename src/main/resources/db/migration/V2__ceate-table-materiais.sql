create table materiais(

    id bigint not null auto_increment,
    nome VARCHAR(100) not null unique,
    densidade DECIMAL(4,3) not null,
    preco DECIMAL(7,2) not null,
    ativo tinyint DEFAULT 1,
    primary key(id)

);
