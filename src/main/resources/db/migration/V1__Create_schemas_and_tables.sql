create schema if not exists tipos;
create schema if not exists produtos;
create schema if not exists compras;
create schema if not exists vendas;
create schema if not exists pessoas;
create schema if not exists caixa;

create table if not exists caixa.caixa (
    id serial primary key,
    data varchar not null,
    valor_inicial real not null,
    valor_atual real,
    em_andamento boolean
);

create table if not exists tipos.permissao (
    id serial primary key,
    nome varchar not null
);

create table if not exists tipos.categoria (
    id serial primary key,
    nome varchar not null
);

create table if not exists tipos.forma_pagamento (
    id serial primary key,
    nome varchar not null
);

create table if not exists pessoas.usuario (
    id serial primary key,
    login varchar not null,
    senha varchar not null,
    nome varchar not null,
    permissao_id integer references tipos.permissao not null
);

create table if not exists pessoas.cliente (
    id serial primary key,
    nome varchar not null,
    valor_pendente real
);

create table if not exists produtos.produto (
    id serial primary key,
    descricao varchar not null,
    categoria_id integer references tipos.categoria not null,
    preco_venda real not null,
    codigo varchar not null
);

create table if not exists produtos.caixa_produto (
    id serial primary key,
    produto_id integer references produtos.produto not null,
    quantidade_produto real not null,
    preco_venda real not null,
    codigo varchar not null
);

create table if not exists produtos.estoque (
    id serial primary key,
    produto_id integer references produtos.produto not null,
    quantidade real not null
);

create table if not exists compras.fornecedor (
    id serial primary key,
    nome varchar not null
);

create table if not exists compras.compra (
    id serial primary key,
    valor_total real not null,
    fornecedor_id integer references compras.fornecedor not null,
    data varchar not null
);

create table if not exists compras.itens_comprados (
    id serial primary key,
    compra_id integer references compras.compra not null,
    produto_id integer references produtos.produto,
    caixa_produto_id integer references produtos.caixa_produto,
    quantidade_produto real,
    quantidade_caixa real,
    valor_unitario real not null
);

create table if not exists vendas.venda (
    id serial primary key,
    valor_total real,
    data varchar not null,
    forma_pagamento_id integer references tipos.forma_pagamento,
    cliente_id integer references pessoas.cliente
);

create table if not exists vendas.itens_vendidos (
    id serial primary key,
    venda_id integer references vendas.venda not null,
    produto_id integer references produtos.produto,
    caixa_produto_id integer references produtos.caixa_produto,
    quantidade_produto real,
    quantidade_caixa real,
    valor_unitario real not null
);