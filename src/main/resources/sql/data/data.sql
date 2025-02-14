INSERT INTO param_dictionary (id, code, label)
VALUES
    (1, 'color', 'Цвет роз'),
    (2, 'stem_length', 'Длина стебля');

INSERT INTO products (id, code, name, price, quantity)
VALUES
    (1, 'ROSE101', 'Розы (101 штука)', 50000.00, 5),
    (2, 'RCH-BOX', 'Подарочный набор конфет', 800.00, 5);

INSERT INTO product_params (id, product_id, param_id, param_value)
VALUES
    (1, 1, 1, 'Красный'),
    (2, 1, 2, '70'),
    (3, 2, 1, 'Белый');
