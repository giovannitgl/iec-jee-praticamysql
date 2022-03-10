create table categoria (
    codigo int primary key auto_increment,
    nome varchar(100)
                       );
create table produto (
    codigo int primary key auto_increment,
    nome varchar(100),
    preco DECIMAL(10, 2),
    categoriaID int not null,
    foreign key (categoriaID) references categoria(codigo)
                     );
