CREATE DATABASE IF NOT EXISTS controle_estoque;
USE controle_estoque;

CREATE TABLE produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo_barras VARCHAR(20) NOT NULL UNIQUE,
    nome VARCHAR(120) NOT NULL,
    categoria VARCHAR(60) NOT NULL,
    preco_unitario DECIMAL(10, 2) NOT NULL,
    quantidade_estoque INT NOT NULL DEFAULT 0,
    estoque_minimo INT NOT NULL DEFAULT 10,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO produtos (codigo_barras, nome, categoria, preco_unitario, quantidade_estoque, estoque_minimo)
VALUES
    ('7891000100103', 'Arroz Tipo 1 5kg', 'Mercearia', 24.90, 120, 30),
    ('7891000200202', 'Feijao Carioca 1kg', 'Mercearia', 7.49, 85, 25),
    ('7891000300301', 'Oleo de Soja 900ml', 'Mercearia', 6.29, 18, 20),
    ('7891000400400', 'Leite Integral 1L', 'Laticinios', 4.89, 200, 50),
    ('7891000500509', 'Cafe Torrado e Moido 500g', 'Matinais', 18.50, 12, 15);
