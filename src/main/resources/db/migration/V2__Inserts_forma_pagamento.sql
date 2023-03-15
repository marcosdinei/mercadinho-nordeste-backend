ALTER TABLE tipos.forma_pagamento
    ADD CONSTRAINT nome_unique UNIQUE (nome);

INSERT INTO tipos.forma_pagamento (nome)
    VALUES ('Cartão de Crédito'),
            ('Cartão de Débito'),
            ('Pix'),
            ('Espécie'),
            ('Fiado'),
            ('Para casa')
    ON CONFLICT
        ON CONSTRAINT nome_unique
    DO NOTHING;
