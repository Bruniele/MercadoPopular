Create database MercadoPopular
default character set utf8mb4
default collate utf8mb4_general_ci;

use MercadoPopular;

CREATE TABLE if not exists Fornecedor (

    codigo_fornecedor INT NOT NULL AUTO_INCREMENT,
    nome_fornecedor VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    telefone VARCHAR(20) NOT NULL,
    endereco VARCHAR(200) NOT NULL,
    numero INT(20),
    cep VARCHAR(10), 
    
    PRIMARY KEY(codigo_fornecedor)
)default charset=utf8;




