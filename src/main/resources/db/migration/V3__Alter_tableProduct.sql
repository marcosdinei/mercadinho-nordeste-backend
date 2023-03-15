ALTER TABLE produtos.produto
    ADD IF NOT EXISTS caixa_produto_id INTEGER
    REFERENCES produtos.caixa_produto;
