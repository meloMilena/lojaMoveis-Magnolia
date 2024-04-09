CREATE TABLE admin(
 id_admin   SERIAL        NOT NULL,
 nome       VARCHAR(35)   NOT NULL,
 senha      VARCHAR(50)   NOT NULL,
 CONSTRAINT pk_admin PRIMARY KEY(id_admin)
);

CREATE TABLE categoria (
  id_categoria  SERIAL       NOT NULL,
  descricao     VARCHAR(50)  NOT NULL,
  nome          VARCHAR(30)  NOT NULL,
  CONSTRAINT pk_categoria PRIMARY KEY(id_categoria)
);

CREATE TABLE endereco (
 id_endereco SERIAL         NOT NULL,
 cep         VARCHAR(8)     NOT NULL,
 bairro      VARCHAR(50)    NOT NULL,
 rua         VARCHAR(50)    NOT NULL,
 numero      INTEGER        NOT NULL,
 complemento VARCHAR(50)    NOT NULL,
 CONSTRAINT pk_endereco PRIMARY KEY (id_endereco)
);

CREATE TABLE pessoa (
 id_pessoa        SERIAL         NOT NULL,
 nome             VARCHAR(50)    NOT NULL,
 email            VARCHAR(35)    NOT NULL,
 cpf              VARCHAR(11)    NOT NULL,
 telefone         VARCHAR(13)    NOT NULL,
 endereco_pessoa  INTEGER        NOT NULL,    
 CONSTRAINT pk_pessoa PRIMARY KEY (id_pessoa),
 CONSTRAINT fk_pessoa_endereco FOREIGN KEY (endereco_pessoa)
    REFERENCES endereco (id_endereco)
);

CREATE TABLE fornecedor (
 id_fornecedor       SERIAL       NOT NULL,
 nome                VARCHAR(35)  NOT NULL,
 email               VARCHAR(35)  NOT NULL,
 cnpj                VARCHAR(14)  NOT NULL,
 cnae                VARCHAR(7)   NOT NULL,
 telefone            VARCHAR(13)  NOT NULL,
 razao_social        VARCHAR(50)  NOT NULL,
 endereco_fornecedor INTEGER      NOT NULL,
 CONSTRAINT pk_fornecedor PRIMARY KEY (id_fornecedor),
 CONSTRAINT fk_fornecedor_endereco  FOREIGN KEY (endereco_fornecedor)
    REFERENCES endereco (id_endereco)
);

CREATE TABLE produto_fornecedor (
 id_produto_fornecedor  SERIAL      NOT NULL,
 quantidade             INTEGER     NOT NULL,
 fornecedor_prod        INTEGER     NOT NULL,
 CONSTRAINT pk_produto_fornecedor PRIMARY KEY (id_produto_fornecedor),
 CONSTRAINT fk_fornecedor_produto FOREIGN KEY (fornecedor_prod)
    REFERENCES fornecedor (id_fornecedor)
);

CREATE TABLE produto (
 id_produto         SERIAL          NOT NULL,
 nome               VARCHAR(50)     NOT NULL,
 preco              NUMERIC         NOT NULL,
 peso               NUMERIC         NOT NULL,
 tamanho            VARCHAR(20)     NOT NULL,
 cod_barras         VARCHAR(35)     NOT NULL,
 cor                VARCHAR(30)     NOT NULL,
 marca              VARCHAR(50)     NOT NULL,
 prod_fornecedor    INTEGER         NOT NULL,
 CONSTRAINT pk_produto PRIMARY KEY (id_produto),
 CONSTRAINT fk_produto_fornecedor FOREIGN KEY (prod_fornecedor)
    REFERENCES produto_fornecedor (id_produto_fornecedor)
);

CREATE TABLE estoque (
 id_produto_estoque     SERIAL      NOT NULL,
 quantidade             INTEGER     NOT NULL,
 prod_estoque           INTEGER     NOT NULL,
 CONSTRAINT pk_estoque PRIMARY KEY (id_produto_estoque),
 CONSTRAINT fk_produto FOREIGN KEY (prod_estoque)
    REFERENCES produto (id_produto)
);


CREATE TABLE gerente (
 id_gerente     SERIAL      NOT NULL,
 salario        NUMERIC     NOT NULL,
 status         VARCHAR(20) NOT NULL,
 pessoa_gerente INTEGER     NOT NULL,
 CONSTRAINT pk_gerente PRIMARY KEY (id_gerente),
 CONSTRAINT fk_gerente_pessoa FOREIGN KEY (pessoa_gerente)
    REFERENCES pessoa (id_pessoa)
);

CREATE TABLE cliente (
 id_cliente      SERIAL      NOT NULL,
 pessoa_cliente  INTEGER     NOT NULL,
 CONSTRAINT pk_cliente PRIMARY KEY(id_cliente),
 CONSTRAINT fk_pessoa_cliente  FOREIGN KEY (pessoa_cliente)
    REFERENCES pessoa (id_pessoa)
);

CREATE TABLE funcionario (
 id_funcionario      SERIAL      NOT NULL,
 salario             NUMERIC     NOT NULL,
 pessoa_funcionario  INTEGER     NOT NULL,
 CONSTRAINT pk_funcionario PRIMARY KEY (id_funcionario),
 CONSTRAINT fk_pessoa_funcionario FOREIGN KEY (pessoa_funcionario)
    REFERENCES pessoa (id_pessoa)
);

CREATE TABLE item_pedido (
 item_pedido    SERIAL     NOT NULL,
 quantidade     INTEGER    NOT NULL,
 preco          NUMERIC    NOT NULL,
 produto_pedido INTEGER    NOT NULL,
 CONSTRAINT pk_item_pedido PRIMARY KEY (item_pedido),
 CONSTRAINT fk_pedido_produto FOREIGN KEY (produto_pedido)
    REFERENCES produto (id_produto)
);

CREATE TABLE pedido (
 id_pedido          SERIAL        NOT NULL,
 valor_total        NUMERIC       NOT NULL,
 data_entrega       DATE          NOT NULL,
 cpf                VARCHAR(11)   NOT NULL,
 data               DATE          NOT NULL,
 contato            VARCHAR(50)   NOT NULL,
 item_ped           INTEGER       NOT NULL,
 funcionario_pedido INTEGER       NOT NULL,
 categoria_pedido   INTEGER       NOT NULL,
 CONSTRAINT pk_pedido PRIMARY KEY (id_pedido),
 CONSTRAINT fk_pedido_catedoria FOREIGN KEY (categoria_pedido)
    REFERENCES categoria (id_categoria),
 CONSTRAINT fk_produto_item FOREIGN KEY (item_ped)
    REFERENCES item_pedido (item_pedido),
 CONSTRAINT fk_funcionario_pedido FOREIGN KEY (funcionario_pedido)
    REFERENCES funcionario (id_funcionario)
);

CREATE TABLE status_pedido (
 id_status_pedido   SERIAL          NOT NULL,
 status_pedido      VARCHAR(50)     NOT NULL,
 pedido_numero      INTEGER         NOT NULL,  
 CONSTRAINT pk_status_pedido PRIMARY KEY (id_status_pedido),
 CONSTRAINT fk_pedido_status FOREIGN KEY (pedido_numero)
    REFERENCES pedido (id_pedido)
);


CREATE TABLE status (
    id_status SERIAL PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL
);

INSERT INTO status (descricao) VALUES
('Em andamento'),
('Cancelado'),
('Saiu para Entrega'),
('Esperando Aprovação');


INSERT INTO admin (nome, senha) VALUES
('admin1', 'senha1'),
('admin2', 'senha2'),
('admin3', 'senha3');

INSERT INTO categoria (descricao, nome) VALUES
('Eletrônicos', 'eletronicos'),
('Roupas', 'roupas'),
('Alimentos', 'alimentos');

INSERT INTO endereco (cep, bairro, rua, numero, complemento) VALUES
('12345678', 'Centro', 'Rua A', 123, 'Apartamento 101'),
('87654321', 'Jardim', 'Rua B', 456, 'Casa 2');

INSERT INTO pessoa (nome, email, cpf, telefone, endereco_pessoa) VALUES
('João Silva', 'joao@example.com', '12345678901', '999999999', 1),
('Maria Souza', 'maria@example.com', '98765432109', '888888888', 2);

INSERT INTO fornecedor (nome, email, cnpj, cnae, telefone, razao_social, endereco_fornecedor) VALUES
('Fornecedor A', 'fornecedorA@example.com', '12345678901234', '1234567', '111111111', 'Razão Social A', 1),
('Fornecedor B', 'fornecedorB@example.com', '98765432109876', '7654321', '222222222', 'Razão Social B', 2);

INSERT INTO produto_fornecedor (quantidade, fornecedor_prod) VALUES
(100, 1),
(200, 2);

INSERT INTO produto (nome, preco, peso, tamanho, cod_barras, cor, marca, prod_fornecedor) VALUES
('Produto A', 50.00, 0.5, 'M', '123456789', 'Azul', 'Marca A', 1),
('Produto B', 100.00, 1.0, 'G', '987654321', 'Vermelho', 'Marca B', 2);

INSERT INTO estoque (quantidade, prod_estoque) VALUES
(50, 1),
(150, 2);

INSERT INTO gerente (salario, status, pessoa_gerente, gerente_admin) VALUES
(5000.00, 'Ativo', 1, 1),
(6000.00, 'Inativo', 2, 2);

INSERT INTO cliente (pessoa_cliente) VALUES
(1),
(2);

INSERT INTO funcionario (salario, pessoa_funcionario) VALUES
(3000.00, 1),
(3500.00, 2);

INSERT INTO item_pedido (quantidade, preco, produto_pedido) VALUES
(2, 100.00, 1),
(3, 150.00, 2);

INSERT INTO pedido (valor_total, data_entrega, cpf, data, contato, item_ped, funcionario_pedido, categoria_pedido) VALUES
(200.00, '2024-04-10', '12345678901', '2024-04-05', 'cliente1@example.com', 1, 1, 1),
(450.00, '2024-04-12', '98765432109', '2024-04-06', 'cliente2@example.com', 2, 2, 2);

INSERT INTO status_pedido (status_pedido, pedido_numero) VALUES
('Em andamento', 1),
('Concluído', 2);


