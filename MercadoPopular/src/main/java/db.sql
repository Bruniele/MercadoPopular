Create database MercadoPopular
default character set utf8mb4
default collate utf8mb4_general_ci;

use MercadoPopular;

CREATE TABLE if not exists Fornecedor (

    id INT AUTO_INCREMENT UNIQUE,
    codigoFornecedor INT(30) NOT NULL,
    nomeFornecedor VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    telefone VARCHAR(20) NOT NULL,
    marca VARCHAR(30) NOT NULL,
    cep VARCHAR(10),
    logradouro VARCHAR(200) NOT NULL,
    bairro VARCHAR(150) NOT NULL,
    localidade VARCHAR(150) NOT NULL,
    uf VARCHAR(3) NOT NULL, 
    
    PRIMARY KEY(codigoFornecedor)
)default charset=utf8;

CREATE TABLE IF NOT EXISTS Categoria(

    id INT AUTO_INCREMENT UNIQUE,
    codigoCategoria INT(30) NOT NULL,
    nomeCategoria VARCHAR(50),
    
    PRIMARY KEY(codigoCategoria)
)default charset=utf8;

INSERT INTO Categoria 
(id, codigoCategoria, nomeCategoria)
VALUES
    (default, 1, 'Vegano'),
    (default, 2, 'Bebida'),
    (default, 3, 'Açougue'),
    (default, 4, 'Frios'),
    (default, 5 ,'Padaria'),
    (default, 6, 'Congelados'),
    (default, 7, 'Hortifruti');






Create table if not exists Produto(
	
    id INT AUTO_INCREMENT UNIQUE,
    codigoProduto INT(30) NOT NULL,
    nomeProduto varchar(100) NOT NULL,
    preco decimal (10,2) NOT NULL,
    descricao text,
    quantidade int(30),
    validade varchar(10),
    codigoFornecedor INT(30) NOT NULL,
    nomeFornecedor varchar(100) NOT NULL,
    codigoCategoria INT(30) NOT NULL,
    nomeCategoria varchar(50) NOT NULL,

    PRIMARY KEY (codigoProduto),
    FOREIGN KEY (codigoFornecedor) REFERENCES Fornecedor(codigoFornecedor),
    FOREIGN KEY (codigoCategoria) REFERENCES Categoria(codigoCategoria)
    
)default charset=utf8;






