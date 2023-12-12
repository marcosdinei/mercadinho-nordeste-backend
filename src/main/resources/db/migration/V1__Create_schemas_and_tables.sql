CREATE SCHEMA IF NOT EXISTS tipos;
CREATE SCHEMA IF NOT EXISTS produtos;
CREATE SCHEMA IF NOT EXISTS compras;
CREATE SCHEMA IF NOT EXISTS vendas;
CREATE SCHEMA IF NOT EXISTS caixa;

CREATE TABLE IF NOT EXISTS caixa.caixa (
    id serial PRIMARY KEY,
    data VARCHAR NOT NULL,
    valor_inicial REAL NOT NULL,
    valor_atual REAL,
    em_andamento BOOLEAN
);

CREATE TABLE IF NOT EXISTS tipos.categoria (
    id serial PRIMARY KEY,
    nome VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS tipos.forma_pagamento (
    id serial PRIMARY KEY,
    nome VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS produtos.produto (
    id serial PRIMARY KEY,
    descricao VARCHAR NOT NULL,
    categoria_id INTEGER REFERENCES tipos.categoria NOT NULL,
    preco_venda REAL NOT NULL,
    codigo VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS produtos.caixa_produto (
    id serial PRIMARY KEY,
    produto_id INTEGER REFERENCES produtos.produto NOT NULL,
    quantidade_produto REAL NOT NULL,
    preco_venda REAL NOT NULL,
    codigo VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS produtos.estoque (
    id serial PRIMARY KEY,
    produto_id INTEGER REFERENCES produtos.produto NOT NULL,
    quantidade REAL NOT NULL
);

CREATE TABLE IF NOT EXISTS compras.fornecedor (
    id serial PRIMARY KEY,
    nome VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS compras.compra (
    id serial PRIMARY KEY,
    valor_total REAL NOT NULL,
    fornecedor_id INTEGER REFERENCES compras.fornecedor NOT NULL,
    data VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS compras.itens_comprados (
    id serial PRIMARY KEY,
    compra_id INTEGER REFERENCES compras.compra,
    produto_id INTEGER REFERENCES produtos.produto,
    caixa_produto BOOLEAN NOT NULL,
    quantidade_item REAL NOT NULL,
    valor_unitario REAL NOT NULL
);

CREATE TABLE IF NOT EXISTS vendas.cliente (
    id serial PRIMARY KEY,
    nome VARCHAR NOT NULL,
    valor_pendente REAL
);

CREATE TABLE IF NOT EXISTS vendas.pagamento_cliente(
    id serial PRIMARY KEY,
    cliente_id INTEGER REFERENCES vendas.cliente NOT NULL,
    data VARCHAR NOT NULL,
    valor_pago REAL NOT NULL
);

CREATE TABLE IF NOT EXISTS vendas.venda (
    id serial PRIMARY KEY,
    valor_total REAL,
    desconto REAL,
    valor_pago REAL,
    data VARCHAR NOT NULL,
    forma_pagamento_id INTEGER REFERENCES tipos.forma_pagamento,
    cliente_id INTEGER REFERENCES vendas.cliente
);

CREATE TABLE IF NOT EXISTS vendas.itens_vendidos (
    id serial PRIMARY KEY,
    venda_id INTEGER REFERENCES vendas.venda,
    produto_id INTEGER REFERENCES produtos.produto,
    caixa_produto BOOLEAN NOT NULL,
    quantidade_item REAL NOT NULL,
    valor_unitario REAL NOT NULL
);