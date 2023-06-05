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
	id INT NOT NULL AUTO_INCREMENT,
    nomeCategoria VARCHAR(50),
    
    PRIMARY KEY(id)
);


INSERT INTO Categoria 
(id, nomeCategoria)
VALUES
(default,'Vegano'),
(default,'Bebida'),
(default,'Açougue'),
(default, 'Frios'),
(default,'Padaria'),
(default,'Congelados'),
(default, 'Hortifruti');






Create table if not exists Produto(
	
    id int not null auto_increment,
	nome_produto varchar(120) NOT NULL,
    preco decimal (10,2) NOT NULL,
    descricao text,
    quantidade int,
    validade varchar(10),
    codigo_fornecedor int not null,
    codigo_categoria int not null,
    PRIMARY KEY (id),
    FOREIGN KEY (codigoFornecedor) REFERENCES Fornecedor(id),
    FOREIGN KEY (codigo_categoria) REFERENCES Categoria(id)
    
)default charset=utf8;






