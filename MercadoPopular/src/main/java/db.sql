Create database MercadoPopular
default character set utf8mb4
default collate utf8mb4_general_ci;

use MercadoPopular;

CREATE TABLE if not exists Fornecedor (

    id INT AUTO_INCREMENT UNIQUE,
    codigoFornecedor INT(30) NOT NULL,
    nomeFornecedor VARCHAR(100) NOT NULL,
    site VARCHAR(100),
    email VARCHAR(100),
    telefone VARCHAR(20) NOT NULL,
    cep VARCHAR(10),
    logradouro VARCHAR(200) NOT NULL,
    bairro VARCHAR(150) NOT NULL,
    localidade VARCHAR(150) NOT NULL,
    uf VARCHAR(3) NOT NULL, 
    numero VARCHAR(10),
    
    PRIMARY KEY(codigoFornecedor)
)default charset=utf8;

INSERT INTO Fornecedor 
(id, codigoFornecedor, nomeFornecedor, site, email, telefone, cep, logradouro, 
    bairro, localidade, uf, numero) 
VALUES
    (default, 1, 'Nestlé', 'nestle.com.br', 'nestle@nestle.com', 
        '(11)95508-4400', '04730-090', 'Avenida das Nações Unidas', 
        'Várzea de Baixo', 'São Paulo', 'SP', '17007'),
    (default, 2, 'Oba Hortifruti', 'obahortifruti.com.br', 
        'obahortifruti@Obahortifruti.com', '(19)97171-6816', '01227-000', 
        'Avenida Angélica', 'Santa Cecília', 'São Paulo', 'SP', '635');

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
    (default, 5, 'Padaria'),
    (default, 6, 'Congelados'),
    (default, 7, 'Hortifruti');


CREATE TABLE IF NOT EXISTS funcionario(
    id INT AUTO_INCREMENT UNIQUE,
    codigoFuncionario INT(30) NOT NULL,
    nome VARCHAR(40) NOT NULL, 
    email VARCHAR(45),
    telefone VARCHAR(16), 
    rg VARCHAR(20) NOT NULL UNIQUE,
    cpf VARCHAR(20) NOT NULL UNIQUE, 
    setor VARCHAR(60),
    salario DOUBLE,     
    PRIMARY KEY(codigoFuncionario)
)charset = utf8;

INSERT INTO funcionario
(id, codigoFuncionario, nome, email, telefone, rg, cpf, setor, salario) 
VALUES
    (default, 1, 'Marcos Almeida dos Santos', 'marcosdossantos@gmail.com', 
        '(11)98765-9907', '21.993.945-99', '236.982.922-11', 
        'Atendente de caixa', 130900),
    (default, 2, 'Carlos Azevedo', 'carlosazevedo@gmail.com', '(11)98611-3216', 
        '44.655.981-02', '322.655.612-55', 'Estoquista', 150275),
    (default, 3, 'Roberta Guimarães', 'robertaguimaraes@gmail.com', 
        '(11)96732-8861', '55.432.336-87', '332.766.900-12', 
        'Atendente de caixa', 130900);

CREATE TABLE IF NOT EXISTS Produto(
	
    id INT AUTO_INCREMENT UNIQUE,
    codigoProduto INT(30) NOT NULL,
    nomeProduto VARCHAR(100) NOT NULL,
    preco DECIMAL (10,2) NOT NULL,
    descricao text,
    quantidade INT(30),
    validade DATE,
    codigoFornecedor INT(30) NOT NULL,
    nomeFornecedor VARCHAR(100) NOT NULL,
    codigoCategoria INT(30) NOT NULL,
    nomeCategoria VARCHAR(50) NOT NULL,

    PRIMARY KEY (codigoProduto),
    FOREIGN KEY (codigoFornecedor) REFERENCES Fornecedor(codigoFornecedor),
    FOREIGN KEY (codigoCategoria) REFERENCES Categoria(codigoCategoria)
    
)default charset=utf8;

INSERT INTO Produto
(id, codigoProduto, nomeProduto, preco, descricao, quantidade, validade, 
    codigoFornecedor, nomeFornecedor, codigoCategoria, nomeCategoria) 
VALUES
    (default, 1, 'Maçã', 3.44, 'Maçã', 155, '2023-06-30', 2, 
        'Oba Hortifruti', 7, 'Hortifruti'),
    (default, 2, 'Chocolate KitKat', 3.99, 'Chocolate Nestlé KitKat', 232, 
        '2023-10-15', 1, 'Nestlé', 5, 'Padaria'),
    (default, 3, 'Iogurte de Morango', 3.29, 'Iogurte Nestlé de Morango', 55, 
        '2023-08-02', 1, 'Nestlé', 4, 'Frios');

Create table if not exists Venda(
    codigoVenda int(20) not null,
    
    codigoProduto INT(30) NOT NULL,
    nomeProduto VARCHAR(100) NOT NULL,
    preco DECIMAL (10,2) NOT NULL,
    quantidade INT(30),
    validade DATE,
    valorTotal DECIMAL(10,2) NOT NULL,
    totalRecebido DECIMAL(10,2) NOT NULL,
    troco DECIMAL(10,2) NOT NULL,
    codigoCliente INT(30) NOT NULL,
    nomeCliente VARCHAR(100) NOT NULL,
    codigoFuncionario INT(30) NOT NULL,
    nomeFuncionario VARCHAR(40) NOT NULL, 
	
    PRIMARY KEY(codigoVenda),
    FOREIGN KEY (codigoFuncionario) REFERENCES funcionario (codigoFuncionario)
);