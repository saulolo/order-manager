 /*
  * Saul Echeverri
  * 24-02-2026
  * Inserts iniciales para pruebas de la tabla customers
 */
INSERT INTO customers (id_customer, created_at, email, full_name, updated_at)
VALUES
  (1, NOW(), 'echeverri@example.com', 'Saul Echeverri', NOW()),
  (2, NOW(), 'vasquez@example.com', 'Felipe Vasquez', NOW()),
  (3, NOW(), 'arenas@example.com', 'Alejandra Arenas', NOW()),
  (4, NOW(), 'zapata@example.com', 'Leidy Zapata', NOW());

 /*
  * Saul Echeverri
  * 24-02-2026
  * Inserts iniciales para pruebas de la tabla products
 */
INSERT INTO products (id_product, created_at, description, name, price, updated_at)
VALUES
  (1, NOW(), 'Smartphone Android con pantalla AMOLED de 6.5 pulgadas y cámara triple', 'Samsung Galaxy S22', 3200.00, NOW()),
  (2, NOW(), 'Laptop ultraliviana con procesador Intel Core i7, 16GB RAM y SSD 512GB', 'Dell XPS 13', 5400.00, NOW()),
  (3, NOW(), 'Tablet con pantalla Retina de 10.2 pulgadas y Apple Pencil compatible', 'iPad 10th Gen', 2200.00, NOW()),
  (4, NOW(), 'Auriculares inalámbricos con cancelación de ruido y batería de larga duración', 'Sony WH-1000XM5', 1300.00, NOW()),
  (5, NOW(), 'Smartwatch resistente al agua con GPS, monitor cardíaco y pantalla AMOLED', 'Amazfit GTR 3 Pro', 900.00, NOW()),
  (6, NOW(), 'Monitor LED 27" Full HD, ideal para oficina y gaming', 'LG UltraGear 27', 780.00, NOW());